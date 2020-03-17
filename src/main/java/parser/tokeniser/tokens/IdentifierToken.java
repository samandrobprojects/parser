package parser.tokeniser.tokens;

public class IdentifierToken extends Token {
    private final String _identifierString;

    public boolean isIdenticalToToken(Token someOtherToken) {
        if (someOtherToken instanceof IdentifierToken) {
            return ((IdentifierToken)someOtherToken)._identifierString.equals(this._identifierString);
        }
        return false;
    }

    private IdentifierToken(String identifierString) {
        _identifierString = identifierString;
    }

    public static IdentifierToken newIdentifierTokenRepresentedWithIdentifierString(String identifierString) {
        return new IdentifierToken(identifierString);
    }
}
