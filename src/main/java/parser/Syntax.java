package parser;

import parser.functional.*;
import parser.tokeniser.AcceptedAtomicToken;
import parser.tokeniser.tokens.AtomicToken;
import parser.tokeniser.tokens.IdentifierToken;
import parser.tokeniser.tokens.Token;

public class Syntax {
    public static Syntax atomicSyntaxAsCharacter(char givenCharacter) {
        AtomicToken newUniqueAtomicToken = AtomicToken.newUniqueAtomicToken();
        return new Syntax(newUniqueAtomicToken, Maybe.asObject(AcceptedAtomicToken.acceptedAtomicTokenWithTokenAndRepresentativeCharacter(newUniqueAtomicToken, givenCharacter)));
    }
    public static Syntax atomicSyntaxAsFirstAndSecondCharacter(char givenFirstCharacter, char givenSecondCharacter) {
        AtomicToken newUniqueAtomicToken = AtomicToken.newUniqueAtomicToken();
        return new Syntax(newUniqueAtomicToken, Maybe.asObject(AcceptedAtomicToken.acceptedAtomicTokenWithTokenAndFirstAndSecondRepresentativeCharacters(newUniqueAtomicToken, givenFirstCharacter, givenSecondCharacter)));

    }
    public static Syntax identifierSyntaxAsString(String givenIdentifierString) {
        return new Syntax(IdentifierToken.newIdentifierTokenRepresentedWithIdentifierString(givenIdentifierString), Maybe.asNothing());
    }
    private final Token _representativeToken;
    private final Maybe<AcceptedAtomicToken> _maybeRepresentativeAcceptedAtomicToken;

    private Syntax(Token representativeToken, Maybe<AcceptedAtomicToken> maybeRepresentativeAcceptedAtomicToken) {
        _representativeToken = representativeToken;
        _maybeRepresentativeAcceptedAtomicToken = maybeRepresentativeAcceptedAtomicToken;
    }

    protected Token getTokenRepresentation() {
        return _representativeToken;
    }
    protected Maybe<AcceptedAtomicToken> maybeGetAcceptedAtomicTokenRepresentation() {
        return _maybeRepresentativeAcceptedAtomicToken;
    }
}