package parser.tokeniser.tokens;

public class AtomicToken extends Token {
    private AtomicToken() {}

    public static AtomicToken newUniqueAtomicToken() {
        return new AtomicToken();
    }
}
