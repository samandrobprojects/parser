package parser.syntax_tree;

import parser.tokeniser.AcceptedAtomicToken;
import parser.tokeniser.tokens.Token;

import java.util.List;

public interface SyntaxRuleset {
    public interface SyntaxCategoryClassifier {
        public void classfiyAsBeginGrouping();
        public void classfiyAsEndGrouping();
        public void classfiyAsInfixOperationWithBindingStrength(int bindingStrength);
        public void classfiyAsPrefixOperationWithBindingStrength(int bindingStrength);
        public void classifyAsValue();
        public void classifyAsInvalid();
    }
    public void classifyTokenIntoSyntaxCategoryWithClassifier(Token tokenToClassify, SyntaxCategoryClassifier syntaxCategoryClassifier);
}
