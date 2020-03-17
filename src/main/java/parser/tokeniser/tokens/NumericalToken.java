package parser.tokeniser.tokens;

public class NumericalToken extends Token {
    private final Double _doubleRepresentationOfNumericalToken;

    public boolean isIdenticalToToken(Token someOtherToken) {
        return false;
    }

    private NumericalToken(Double doubleRepresentationOfToken) {
        _doubleRepresentationOfNumericalToken = doubleRepresentationOfToken;
    }

    public static NumericalToken newNumericalTokenRepresentedWithDoubleRepresentation(Double doubleRepresentation) {
        return new NumericalToken(doubleRepresentation);
    }

    public Double getDoubleRepresentationOfNumericalToken() {
        return _doubleRepresentationOfNumericalToken;
    }
}
