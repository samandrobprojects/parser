package parser.syntax_tree;

import parser.tokeniser.tokens.Token;

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
