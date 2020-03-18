package parser.syntax_tree;

import functional.Maybe;
import parser.tokeniser.tokens.AtomicToken;
import parser.tokeniser.tokens.Token;

public class SyntaxTree {

    public enum Classification {
        INFIX_OPERATION,
        PREFIX_OPERATION,
        VALUE,
        GROUPING
    }

    public String DEBUG_getStringOfTree() {
        String debugStringOfTree = "";
        switch (_classification) {
            case GROUPING: debugStringOfTree += "GROUPING"; break;
            case INFIX_OPERATION: debugStringOfTree += "INFIX_OPERATION"; break;
            case VALUE: debugStringOfTree += "VALUE"; break;
            case PREFIX_OPERATION: debugStringOfTree += "PREFIX_OPERATION"; break;
        }
        debugStringOfTree += "("+String.valueOf(_bindingStrength)+"){ ";
        if (maybeGetLeftChildTree().isNotNothing()) {
            debugStringOfTree += maybeGetLeftChildTree().object().DEBUG_getStringOfTree();
        }
        debugStringOfTree += ", ";
        if (maybeGetRightChildTree().isNotNothing()) {
            debugStringOfTree += maybeGetRightChildTree().object().DEBUG_getStringOfTree();
        }
        debugStringOfTree += " }";
        return debugStringOfTree;
    }

    private final int _bindingStrength;
    private final Token _rootToken;
    private final Classification _classification;

    public Token getRootToken() {
        return _rootToken;
    }

    public int getBindingStrength() {
        return _bindingStrength;
    }

    public Classification getClassification() {
        return _classification;
    }

    public boolean syntaxTreeBindingIsWeakerThenGivenBindingStrength(int bindingStrength) {
        if (_classification == Classification.PREFIX_OPERATION || _classification == Classification.INFIX_OPERATION) {
            return _bindingStrength < bindingStrength;
        }
        return true;
    }

    public static SyntaxTree newSyntaxTreeRepresentingInfixOperationWithRootTokenAndBindingStrength(Token rootToken, int bindingStrength) {
        return new SyntaxTree(Classification.INFIX_OPERATION, rootToken, bindingStrength);
    }

    public static SyntaxTree newSyntaxTreeRepresentingPrefixOperationWithRootTokenAndBindingStrength(Token rootToken, int bindingStrength) {
        return new SyntaxTree(Classification.PREFIX_OPERATION, rootToken, bindingStrength);
    }

    public static SyntaxTree newSyntaxTreeRepresentingValueWithRootToken(Token rootToken) {
        return new SyntaxTree(Classification.VALUE, rootToken, 0);
    }

    public static SyntaxTree newSyntaxTreeRepresentingGrouping() {
        return new SyntaxTree(Classification.GROUPING, AtomicToken.newUniqueAtomicToken(), 0);
    }

    public boolean syntaxTreeIsComplete() {
        boolean expectingLeftTree = (_classification == Classification.GROUPING || _classification == Classification.PREFIX_OPERATION || _classification == Classification.INFIX_OPERATION);
        boolean expectingRightTree = (_classification == Classification.INFIX_OPERATION);
        return _treeExistanceExpectectationMatchesMaybeTree(expectingLeftTree, this.maybeGetLeftChildTree())
                && _treeExistanceExpectectationMatchesMaybeTree(expectingRightTree, this.maybeGetRightChildTree());
    }

    private boolean _treeExistanceExpectectationMatchesMaybeTree(boolean treeExists, Maybe<SyntaxTree> treeInQuestion) {
        if (treeExists) {
            if (treeInQuestion.isNotNothing()) {
                return treeInQuestion.object().syntaxTreeIsComplete();
            } else {
                return false;
            }
        } else {
            return treeInQuestion.isNothing();
        }
    }

    private SyntaxTree(Classification classification, Token rootToken, int bindingStrength) {
        _bindingStrength = bindingStrength;
        _rootToken = rootToken;
        _classification = classification;
    }

    private Maybe<SyntaxTree> _maybeParentTree = Maybe.asNothing();
    private Maybe<SyntaxTree> _maybeLeftChild = Maybe.asNothing();
    private Maybe<SyntaxTree> _maybeRightChild = Maybe.asNothing();

    public void setParentTree(SyntaxTree givenTree) {
        _maybeParentTree = Maybe.asObject(givenTree);
    }

    public void setLeftChildTree(SyntaxTree givenTree) {
        _maybeLeftChild = Maybe.asObject(givenTree);
    }

    public void setRightChildTree(SyntaxTree givenTree) {
        _maybeRightChild = Maybe.asObject(givenTree);
    }

    public Maybe<SyntaxTree> maybeGetParentTree() {
        return _maybeParentTree;
    }

    public Maybe<SyntaxTree> maybeGetLeftChildTree() {
        return _maybeLeftChild;
    }

    public Maybe<SyntaxTree> maybeGetRightChildTree() {
        return _maybeRightChild;
    }
}
