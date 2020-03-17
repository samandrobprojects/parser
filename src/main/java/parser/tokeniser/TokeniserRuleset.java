package parser.tokeniser;

import java.util.List;

public interface TokeniserRuleset {
    public boolean acceptNumericalTokens();
    public boolean acceptIdentifierTokens();
    public List<AcceptedAtomicToken> listOfAcceptedAtomicTokensInPriorityOrder();
}
