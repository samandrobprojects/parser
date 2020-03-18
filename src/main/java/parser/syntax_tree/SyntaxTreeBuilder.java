package parser.syntax_tree;

import parser.tokeniser.TokeniserException;
import parser.tokeniser.tokens.AtomicToken;
import parser.tokeniser.tokens.Token;
import parser.functional.Maybe;

import java.util.List;

public class SyntaxTreeBuilder {
    private SyntaxRuleset _syntaxRuleset;

    public static SyntaxTreeBuilder syntaxTreeBuilderWithSyntaxRuleset(SyntaxRuleset syntaxRuleset) {
        return new SyntaxTreeBuilder(syntaxRuleset);
    }

    private SyntaxTreeBuilder(SyntaxRuleset syntaxRuleset) {
        _syntaxRuleset = syntaxRuleset;
    }

    private SyntaxTree _rootOfSyntaxTreeBeingBuilt;
    private SyntaxTree _currentTreePositionInSyntaxTreeBeingBuilt;

    public SyntaxTree buildSyntaxTreeFromListOfTokens(List<Token> tokensToBuildTreeFrom) throws SyntaxTreeBuilderException {
        _rootOfSyntaxTreeBeingBuilt = _newRootSyntaxTree();
        _currentTreePositionInSyntaxTreeBeingBuilt = _rootOfSyntaxTreeBeingBuilt;
        for (Token nextTokenToBuildSyntaxTree : tokensToBuildTreeFrom) {
            _introduceNewTokenIntoTreeCurrentlyBeingBuilt(nextTokenToBuildSyntaxTree);
        }
        if (!_rootOfSyntaxTreeBeingBuilt.syntaxTreeIsComplete()) {
            System.out.println("buildSyntaxTreeFromListOfTokens: " +  _rootOfSyntaxTreeBeingBuilt.DEBUG_getStringOfTree());
            throw new SyntaxTreeBuilderException("cannot parse, invalid syntax, is not complete");
        }
        return _rootOfSyntaxTreeBeingBuilt;
    }

    private SyntaxTree _newRootSyntaxTree() {
        return SyntaxTree.newSyntaxTreeRepresentingGrouping();
    }

    private void _introduceNewTokenIntoTreeCurrentlyBeingBuilt(final Token newTokenToIntroduce) throws SyntaxTreeBuilderException {
        _syntaxRuleset.classifyTokenIntoSyntaxCategoryWithClassifier(newTokenToIntroduce, new SyntaxRuleset.SyntaxCategoryClassifier() {
            @Override
            public void classfiyAsBeginGrouping() {
                SyntaxTree syntaxTreeRepresentingGrouping = SyntaxTree.newSyntaxTreeRepresentingGrouping();
                _introduceValueOrGroupingOrPrefixOperationToSyntaxTreeBeingBuilt(syntaxTreeRepresentingGrouping);
            }
            @Override
            public void classfiyAsEndGrouping() {
                _introduceEndGroupInSyntaxTreeCurrentlyBeingBuilt();
            }
            @Override
            public void classfiyAsInfixOperationWithBindingStrength(int bindingStrength) {
                SyntaxTree syntaxTreeRepresentingInfixOperation = SyntaxTree.newSyntaxTreeRepresentingInfixOperationWithRootTokenAndBindingStrength(newTokenToIntroduce, bindingStrength);
                _introduceInfixOperationToSyntaxTreeBeingBuilt(syntaxTreeRepresentingInfixOperation);
            }
            @Override
            public void classfiyAsPrefixOperationWithBindingStrength(int bindingStrength) {
                SyntaxTree syntaxTreeRepresentingPrefixOperation = SyntaxTree.newSyntaxTreeRepresentingPrefixOperationWithRootTokenAndBindingStrength(newTokenToIntroduce, bindingStrength);
                _introduceValueOrGroupingOrPrefixOperationToSyntaxTreeBeingBuilt(syntaxTreeRepresentingPrefixOperation);
            }
            @Override
            public void classifyAsValue() {
                SyntaxTree syntaxTreeRepresentingValue = SyntaxTree.newSyntaxTreeRepresentingValueWithRootToken(newTokenToIntroduce);
                _introduceValueOrGroupingOrPrefixOperationToSyntaxTreeBeingBuilt(syntaxTreeRepresentingValue);
            }
            @Override
            public void classifyAsInvalid() {
                throw new SyntaxTreeBuilderException("invalid token found when parsing");
            }
        });
    }

    private void _introduceValueOrGroupingOrPrefixOperationToSyntaxTreeBeingBuilt(SyntaxTree newTreeToIntroduce) throws SyntaxTreeBuilderException {
        if (_currentTreePositionInSyntaxTreeBeingBuilt.getClassification() == SyntaxTree.Classification.GROUPING) {
            _currentTreePositionInSyntaxTreeBeingBuilt.setLeftChildTree(newTreeToIntroduce);
            newTreeToIntroduce.setParentTree(_currentTreePositionInSyntaxTreeBeingBuilt);
            _currentTreePositionInSyntaxTreeBeingBuilt = newTreeToIntroduce;

        } else if (_currentTreePositionInSyntaxTreeBeingBuilt.getClassification() == SyntaxTree.Classification.PREFIX_OPERATION) {
            _currentTreePositionInSyntaxTreeBeingBuilt.setLeftChildTree(newTreeToIntroduce);
            newTreeToIntroduce.setParentTree(_currentTreePositionInSyntaxTreeBeingBuilt);
            _currentTreePositionInSyntaxTreeBeingBuilt = newTreeToIntroduce;

        } else if (_currentTreePositionInSyntaxTreeBeingBuilt.getClassification() == SyntaxTree.Classification.INFIX_OPERATION) {
            _currentTreePositionInSyntaxTreeBeingBuilt.setRightChildTree(newTreeToIntroduce);
            newTreeToIntroduce.setParentTree(_currentTreePositionInSyntaxTreeBeingBuilt);
            _currentTreePositionInSyntaxTreeBeingBuilt = newTreeToIntroduce;

        } else {
            throw new SyntaxTreeBuilderException("invalid syntax");
        }
    }

    private void _introduceEndGroupInSyntaxTreeCurrentlyBeingBuilt() {
        if (_currentTreePositionInSyntaxTreeBeingBuilt.getClassification() == SyntaxTree.Classification.VALUE
            || _currentTreePositionInSyntaxTreeBeingBuilt.getClassification() == SyntaxTree.Classification.GROUPING) {
            do {
                if (_currentTreePositionInSyntaxTreeBeingBuilt.maybeGetParentTree().isNotNothing()) {
                    _currentTreePositionInSyntaxTreeBeingBuilt = _currentTreePositionInSyntaxTreeBeingBuilt.maybeGetParentTree().object();
                } else {
                    throw new SyntaxTreeBuilderException("invalid syntax, grouping ended badly");
                }
            } while (_currentTreePositionInSyntaxTreeBeingBuilt.getClassification() != SyntaxTree.Classification.GROUPING);

        } else {
            throw new SyntaxTreeBuilderException("invalid syntax, unexpected grouping end");
        }
    }

    private void _introduceInfixOperationToSyntaxTreeBeingBuilt(SyntaxTree infixOperationToIntroduce) throws SyntaxTreeBuilderException {
        if (_currentTreePositionInSyntaxTreeBeingBuilt.getClassification() == SyntaxTree.Classification.GROUPING ||
                _currentTreePositionInSyntaxTreeBeingBuilt.getClassification() == SyntaxTree.Classification.VALUE) {
            if (_currentTreePositionInSyntaxTreeBeingBuilt.maybeGetParentTree().isNotNothing()) {
                while (!_currentTreePositionInSyntaxTreeBeingBuilt.maybeGetParentTree().object().syntaxTreeBindingIsWeakerThenGivenBindingStrength(infixOperationToIntroduce.getBindingStrength())) {
                    _currentTreePositionInSyntaxTreeBeingBuilt = _currentTreePositionInSyntaxTreeBeingBuilt.maybeGetParentTree().object();
                    if (_currentTreePositionInSyntaxTreeBeingBuilt.maybeGetParentTree().isNothing()) {
                        throw new SyntaxTreeBuilderException("invalid syntax..");
                    }
                }
                _insertSyntaxTreeInCurrentTreePositionPushingExistingCurrentTreeToLeftBranch(infixOperationToIntroduce);
            } else {
                throw new SyntaxTreeBuilderException("invalid syntax .");
            }

        } else {
            throw new SyntaxTreeBuilderException("invalid syntax, unexpected operation");
        }
    }

    private void _insertSyntaxTreeInCurrentTreePositionPushingExistingCurrentTreeToLeftBranch(SyntaxTree syntaxTreeToPushIn) {
        if (_currentTreePositionInSyntaxTreeBeingBuilt.maybeGetParentTree().isNotNothing()) {
            SyntaxTree parentOfCurrentTreePosition = _currentTreePositionInSyntaxTreeBeingBuilt.maybeGetParentTree().object();
            syntaxTreeToPushIn.setParentTree(parentOfCurrentTreePosition);
            boolean currentTreePositionIsOnRightBranchOfItsParent = parentOfCurrentTreePosition.maybeGetRightChildTree().containsObjectEqualTo(_currentTreePositionInSyntaxTreeBeingBuilt);
            if (currentTreePositionIsOnRightBranchOfItsParent) {
                parentOfCurrentTreePosition.setRightChildTree(syntaxTreeToPushIn);
            } else {
                parentOfCurrentTreePosition.setLeftChildTree(syntaxTreeToPushIn);
            }
            _currentTreePositionInSyntaxTreeBeingBuilt.setParentTree(syntaxTreeToPushIn);
            syntaxTreeToPushIn.setLeftChildTree(_currentTreePositionInSyntaxTreeBeingBuilt);
            _currentTreePositionInSyntaxTreeBeingBuilt = syntaxTreeToPushIn;
        } else {
            throw new SyntaxTreeBuilderException("invalid syntax, ");
        }
    }
}
