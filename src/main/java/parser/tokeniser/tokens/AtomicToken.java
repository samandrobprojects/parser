package parser.tokeniser.tokens;

public class AtomicToken extends Token {
    private AtomicToken() {}

    public static AtomicToken newUniqueAtomicToken() {
        return new AtomicToken();
    }

/*
    public static final AtomicToken RHUBARB_TOKEN = new AtomicToken();
    public static final AtomicToken ADD_TOKEN = new AtomicToken();
    public static final AtomicToken SUBTRACT_TOKEN = new AtomicToken();
    public static final AtomicToken MULTIPLY_TOKEN = new AtomicToken();
    public static final AtomicToken DIVIDE_TOKEN = new AtomicToken();
    public static final AtomicToken OPEN_BRACKET_TOKEN = new AtomicToken();
    public static final AtomicToken CLOSED_BRACKET_TOKEN = new AtomicToken();
*/
}
