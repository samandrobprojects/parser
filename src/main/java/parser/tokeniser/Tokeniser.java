package parser.tokeniser;

import parser.Emitter;
import parser.functional.*;
import parser.tokeniser.tokens.IdentifierToken;
import parser.tokeniser.tokens.NumericalToken;
import parser.tokeniser.tokens.Token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Tokeniser {
    private boolean _tokeniserAcceptsNumericalTokens;
    private boolean _tokeniserAcceptsIdentifierTokens;
    private List<AcceptedAtomicToken> _listOfAcceptedAtomicTokensInPriorityOrder;

    public static Tokeniser createTokeniserWithTokeniserRuleset(TokeniserRuleset givenTokeniserRuleset) {
        return new Tokeniser(givenTokeniserRuleset);
    }

    private Tokeniser(TokeniserRuleset givenTokeniserRuleset) {
        _tokeniserAcceptsNumericalTokens = givenTokeniserRuleset.acceptNumericalTokens();
        _tokeniserAcceptsIdentifierTokens = givenTokeniserRuleset.acceptIdentifierTokens();
        _listOfAcceptedAtomicTokensInPriorityOrder = givenTokeniserRuleset.listOfAcceptedAtomicTokensInPriorityOrder();
    }

    private interface TokenReceiver {
        public void sendToken(Token receivedToken);
    }

    public List<Token> tokeniseStringIntoListOfTokens(String stringToTokenise) throws TokeniserException {
        ArrayList<Token> listOfTokensParsed = new ArrayList<>();
        _tokeniseEmitterCharacterStreamAndProvideTokensToReceiver(Emitter.emitterWithObjectsToEmitInOrder(_stringToCharacterList(stringToTokenise)), new TokenReceiver() {
            @Override
            public void sendToken(Token receivedToken) {
                listOfTokensParsed.add(receivedToken);
            }
        });
        return listOfTokensParsed;
    }

    private List<Character> _stringToCharacterList(String string) {
        ArrayList<Character> characterList = new ArrayList<>();
        for (char character : string.toCharArray()) {
            characterList.add(character);
        }
        return characterList;
    }

    private Emitter<Character> _characterEmitter;
    private TokenReceiver _tokenReceiver;
    private void _tokeniseEmitterCharacterStreamAndProvideTokensToReceiver(Emitter<Character> characterEmitter, TokenReceiver tokenReceiver) throws TokeniserException {
        _startRoundOfTokenisingWithCharacterEmitterAndTokenReceiver(characterEmitter, tokenReceiver);
        while (_characterEmitter.hasMoreToEmit()) {
            //System.out.print("Character this round: ");
            //System.out.println(_characterEmitter.maybePeekAtNext().object());
            if (_tokeniserAcceptsNumericalTokens) {
                _tokeniseNextNumericalTokenIfExists();
            }
            if (!_tokenHasBeenParsedThisRoundOfTokenising()) {
                _tokeniseNextAtomicTokenIfExists();
            }
            if (!_tokenHasBeenParsedThisRoundOfTokenising() && _tokeniserAcceptsIdentifierTokens) {
                _tokeniseNextIdentifierTokenIfExists();
            }
            _consumeAllNextEmittedWhitespaceCharacters();
            if (_characterEmitter.hasMoreToEmit() && !_tokenHasBeenParsedThisRoundOfTokenising()) {
                throw new TokeniserException("cannot parse");
            }
            _startRoundOfTokenisingWithCharacterEmitterAndTokenReceiver(characterEmitter, tokenReceiver);
        }
    }

    private void _tokeniseNextNumericalTokenIfExists() {
        boolean nextTokenIsNumericalAndStartsWithNegative = _nextEmittedCharacterIsIdenticalTo('-') && _maybeCharacterIsNumeric(_maybeNextOverEmittedCharacter());
        boolean nextTokenIsNumerical = nextTokenIsNumericalAndStartsWithNegative || _maybeCharacterIsNumeric(_maybeNextEmittedCharacter());
        if (nextTokenIsNumerical) {
            String stringRepresentingNumericalTokenBeingParsed = "";
            if (nextTokenIsNumericalAndStartsWithNegative) {
                stringRepresentingNumericalTokenBeingParsed += _consumeNextEmittedCharacter();
            }
            stringRepresentingNumericalTokenBeingParsed += _consumeNextEmittedSequenceOfNumericDigits();
            boolean nextCharactersAreDecimalPointAndThenNumber = _nextEmittedCharacterIsIdenticalTo('.') && _maybeCharacterIsNumeric(_maybeNextOverEmittedCharacter());
            if (nextCharactersAreDecimalPointAndThenNumber) {
                stringRepresentingNumericalTokenBeingParsed += _consumeNextEmittedCharacter();
                stringRepresentingNumericalTokenBeingParsed += _consumeNextEmittedSequenceOfNumericDigits();
            }
            double numericTokenAsDouble = Double.parseDouble(stringRepresentingNumericalTokenBeingParsed);
            //System.out.printf("_tokeniseNextNumericalTokenIfExists: %f, %s\n", numericTokenAsDouble, stringRepresentingNumericalTokenBeingParsed);
            _provideNextToken(NumericalToken.newNumericalTokenRepresentedWithDoubleRepresentation(numericTokenAsDouble));
        }
    }

    private void _tokeniseNextIdentifierTokenIfExists() {
        boolean nextTokenIsIdentifier = _maybeCharacterIsLetterOrUnderscore(_maybeNextEmittedCharacter());
        if (nextTokenIsIdentifier) {
            String nextIdentifierTokenAsString = _consumeNextEmittedSequenceOfNumericAndLetterAndUnderscoreCharacters();
            _provideNextToken(IdentifierToken.newIdentifierTokenRepresentedWithIdentifierString(nextIdentifierTokenAsString));
        }
    }

    private void _tokeniseNextAtomicTokenIfExists() {
        Maybe<AcceptedAtomicToken> firstAcceptedAtomicToken = _maybeConsumeNextAcceptedAtomicTokenInPriorityListOrderIfExists();
        //System.out.println("_tokeniseNextAtomicTokenIfExists: atomomin");
        if (firstAcceptedAtomicToken.isNotNothing()) {
            //System.out.println("_tokeniseNextAtomicTokenIfExists: accepted atomomin");
            _provideNextToken(firstAcceptedAtomicToken.object().getAtomicTokenRepresentation());
        }
    }

    private Maybe<AcceptedAtomicToken> _maybeConsumeNextAcceptedAtomicTokenInPriorityListOrderIfExists() {
        for (AcceptedAtomicToken acceptedAtomicToken : _listOfAcceptedAtomicTokensInPriorityOrder) {
            Character firstCharacterOfAtomicToken = acceptedAtomicToken.getFirstCharacterOfAtomicToken();
            //System.out.printf("_maybeConsumeNextAcceptedAtomicTokenInPriorityListOrderIfExists: char %c\n",firstCharacterOfAtomicToken);
            Maybe<Character> maybeSecondCharacterOfAtomicToken = acceptedAtomicToken.maybeGetSecondCharacterOfAtomicToken();
            boolean acceptedAtomicTokenIsNextToken = _nextEmittedCharacterIsIdenticalTo(firstCharacterOfAtomicToken) && (maybeSecondCharacterOfAtomicToken.isNothing() || _nextOverEmittedCharacterIsIdenticalToCharaterInsideMaybe(maybeSecondCharacterOfAtomicToken));
            if (acceptedAtomicTokenIsNextToken) {
                _consumeNextEmittedCharacter();
                if (maybeSecondCharacterOfAtomicToken.isNotNothing()) {
                    _consumeNextEmittedCharacter();
                }
                _provideNextToken(acceptedAtomicToken.getAtomicTokenRepresentation());
            }
        }
        return Maybe.asNothing();
    }

    private Maybe<Character> _maybeNextEmittedCharacter() {
        return _characterEmitter.maybePeekAtNext();
    }

    private Maybe<Character> _maybeNextOverEmittedCharacter() {
        return _characterEmitter.maybePeekAtNextOver();
    }

    private boolean _nextEmittedCharacterIsIdenticalTo(char givenCharacter) {
        Maybe<Character> nextCharacter = _characterEmitter.maybePeekAtNext();
        if (nextCharacter.isNotNothing()) {
            return ((char)nextCharacter.object()) == givenCharacter;
        }
        return false;
    }

    private boolean _nextOverEmittedCharacterIsIdenticalToCharaterInsideMaybe(Maybe<Character> givenMaybeCharacter) {
        Maybe<Character> nextOverCharacter = _characterEmitter.maybePeekAtNextOver();
        if (nextOverCharacter.isNotNothing()) {
            return givenMaybeCharacter.containsObjectEqualTo(nextOverCharacter.object());
        }
        return false;
    }

    private boolean _maybeCharacterIsNumeric(Maybe<Character> maybeCharacterInQuestion) {
        return maybeCharacterInQuestion.applyGivenOperationOntoThisObjectMondically(new MonadicOperation<Monad<Boolean>, Character, Boolean>() {
            @Override
            public Maybe<Boolean> performMonadicOperation(Character characterInQuestion) {
                return Maybe.asObject(Character.isDigit(characterInQuestion));
            }
        }).containsObjectEqualTo(true);
    }

    private boolean _maybeCharacterIsWhitespace(Maybe<Character> maybeCharacterInQuestion) {
        return maybeCharacterInQuestion.applyGivenOperationOntoThisObjectMondically(new MonadicOperation<Monad<Boolean>, Character, Boolean>() {
            @Override
            public Maybe<Boolean> performMonadicOperation(Character characterInQuestion) {
                return Maybe.asObject(Character.isWhitespace(characterInQuestion));
            }
        }).containsObjectEqualTo(true);
    }

    private boolean _maybeCharacterIsLetterOrUnderscore(Maybe<Character> maybeCharacterInQuestion) {
        return maybeCharacterInQuestion.applyGivenOperationOntoThisObjectMondically(new MonadicOperation<Monad<Boolean>, Character, Boolean>() {
            @Override
            public Maybe<Boolean> performMonadicOperation(Character characterInQuestion) {
                return Maybe.asObject(Character.isLetter(characterInQuestion));
            }
        }).containsObjectEqualTo(true);
    }

    private Character _consumeNextEmittedCharacter() {
        Maybe<Character> nextCharacter = _characterEmitter.maybeEmitNext();
        if (nextCharacter.isNotNothing()) {
            return nextCharacter.object();
        } else {
            throw new TokeniserException("token internally emitted no character");
        }
    }

    private void _consumeAllNextEmittedWhitespaceCharacters() {
        while (_maybeCharacterIsWhitespace(_maybeNextEmittedCharacter())) {
            _consumeNextEmittedCharacter();
        }
    }

    private String _consumeNextEmittedSequenceOfNumericDigits() {
        String consumedSequence = "";
        while (_maybeCharacterIsNumeric(_maybeNextEmittedCharacter())) {
            consumedSequence += _consumeNextEmittedCharacter();
        }
        return consumedSequence;
    }

    private String _consumeNextEmittedSequenceOfNumericAndLetterAndUnderscoreCharacters() {
        String consumedSequence = "";
        while (_maybeCharacterIsNumeric(_maybeNextEmittedCharacter()) || _maybeCharacterIsLetterOrUnderscore(_maybeNextEmittedCharacter())) {
            consumedSequence += _consumeNextEmittedCharacter();
        }
        return consumedSequence;
    }

    private boolean _tokenHasBeenParsedThisRoundOfTokenising = false;

    private void _startRoundOfTokenisingWithCharacterEmitterAndTokenReceiver(Emitter<Character> characterEmitter, final TokenReceiver tokenReceiver) {
        _tokenHasBeenParsedThisRoundOfTokenising = false;
        _characterEmitter = characterEmitter;
        _tokenReceiver = new TokenReceiver() {
            @Override
            public void sendToken(Token receivedToken) {
                _tokenHasBeenParsedThisRoundOfTokenising = true;
                tokenReceiver.sendToken(receivedToken);
            }
        };
    }

    private boolean _tokenHasBeenParsedThisRoundOfTokenising() {
        return _tokenHasBeenParsedThisRoundOfTokenising;
    }

    private void _provideNextToken(Token tokenInQuestion) {
        _tokenReceiver.sendToken(tokenInQuestion);
    }
}
