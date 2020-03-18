package parser.tokeniser;

import parser.functional.*;
import parser.tokeniser.tokens.AtomicToken;

public class AcceptedAtomicToken {
    private final AtomicToken _atomicTokenRepresentation;
    private final Character _firstCharacterOfAtomicToken;
    private final Maybe<Character> _maybeSecondCharacterOfAtomicToken;

    public static AcceptedAtomicToken acceptedAtomicTokenWithTokenAndRepresentativeCharacter(AtomicToken givenAtomicToken, char representativeCharacter) {
        return new AcceptedAtomicToken(givenAtomicToken, representativeCharacter, Maybe.asNothing());
    }

    public static AcceptedAtomicToken acceptedAtomicTokenWithTokenAndFirstAndSecondRepresentativeCharacters(AtomicToken givenAtomicToken, char firstRepresentativeCharacter, char secondRepresentativeCharacter) {
        return new AcceptedAtomicToken(givenAtomicToken, firstRepresentativeCharacter, Maybe.asObject(secondRepresentativeCharacter));
    }

    private AcceptedAtomicToken(AtomicToken atomicTokenRepresentation, Character firstCharacterOfAtomicToken, Maybe<Character> maybeSecondCharacterOfAtomicToken) {
        _atomicTokenRepresentation = atomicTokenRepresentation;
        _firstCharacterOfAtomicToken = firstCharacterOfAtomicToken;
        _maybeSecondCharacterOfAtomicToken = maybeSecondCharacterOfAtomicToken;
    }

    public AtomicToken getAtomicTokenRepresentation() {
        return _atomicTokenRepresentation;
    }

    public Character getFirstCharacterOfAtomicToken() {
        return _firstCharacterOfAtomicToken;
    }

    public Maybe<Character> maybeGetSecondCharacterOfAtomicToken() {
        return _maybeSecondCharacterOfAtomicToken;
    }
}
