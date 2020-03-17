package parser.evaluator;

import com.sun.tools.corba.se.idl.constExpr.EvaluationException;
import functional.Maybe;
import functional.Monad;
import functional.MonadicOperation;
import parser.syntax_tree.SyntaxTree;
import parser.tokeniser.TokeniserRuleset;
import parser.tokeniser.TokeniserException;
import parser.tokeniser.tokens.Token;

import java.util.List;

public class SyntaxTreeEvaluator<EVALUATED_VALUE> {
    private EvaluationRuleset<EVALUATED_VALUE> _evaluationRuleset;

    public static <EVALUATED_VALUE> SyntaxTreeEvaluator<EVALUATED_VALUE> syntaxTreeEvaluatorWithEvaluationRuleset(EvaluationRuleset<EVALUATED_VALUE> evaluationRuleset) {
        return new SyntaxTreeEvaluator(evaluationRuleset);
    }

    private SyntaxTreeEvaluator(EvaluationRuleset<EVALUATED_VALUE> evaluationRuleset) {
        _evaluationRuleset = evaluationRuleset;
    }

    public EVALUATED_VALUE evaluateSyntaxTree(SyntaxTree syntaxTreeToEvaluate) throws EvaluationException {
        Maybe<EVALUATED_VALUE> maybeEvaulatedValue = _maybeEvaluateSyntaxTree(syntaxTreeToEvaluate);
        if (maybeEvaulatedValue.isNotNothing()) {
            return maybeEvaulatedValue.object();
        } else {
            throw new EvaluationException("evaluation failed");
        }
    }

    private Maybe<EVALUATED_VALUE> _maybeEvaluateSyntaxTree(final SyntaxTree syntaxTreeToEvaluate) {
        if (syntaxTreeToEvaluate.getClassification() == SyntaxTree.Classification.GROUPING) {
            return syntaxTreeToEvaluate.maybeGetLeftChildTree().applyGivenOperationOntoThisObjectMondically(new MonadicOperation<Monad<EVALUATED_VALUE>, SyntaxTree, EVALUATED_VALUE>() {
                @Override
                public Maybe<EVALUATED_VALUE> performMonadicOperation(SyntaxTree leftBranchOfSyntaxTreeToEvaluate) {
                    return _maybeEvaluateSyntaxTree(leftBranchOfSyntaxTreeToEvaluate);
                }
            });

        } else if (syntaxTreeToEvaluate.getClassification() == SyntaxTree.Classification.VALUE) {
            return _evaluationRuleset.maybeValueRepresentationOfValueToken(syntaxTreeToEvaluate.getRootToken());

        } else if (syntaxTreeToEvaluate.getClassification() == SyntaxTree.Classification.PREFIX_OPERATION) {
            return _maybeEvaluateMaybeSyntaxTree(syntaxTreeToEvaluate.maybeGetLeftChildTree()).applyGivenOperationOntoThisObjectMondically(new MonadicOperation<Monad<EVALUATED_VALUE>, EVALUATED_VALUE, EVALUATED_VALUE>() {
                @Override
                public Maybe<EVALUATED_VALUE> performMonadicOperation(EVALUATED_VALUE leftBranchValueOfSyntaxTreeToEvaluate) {
                    return _evaluationRuleset.maybeValueResultOfApplyingPrefixTokenToValue(syntaxTreeToEvaluate.getRootToken(), leftBranchValueOfSyntaxTreeToEvaluate);
                }
            });

        } else if (syntaxTreeToEvaluate.getClassification() == SyntaxTree.Classification.INFIX_OPERATION) {
            return _maybeEvaluateMaybeSyntaxTree(syntaxTreeToEvaluate.maybeGetLeftChildTree()).applyGivenOperationOntoThisObjectMondically(new MonadicOperation<Monad<EVALUATED_VALUE>, EVALUATED_VALUE, EVALUATED_VALUE>() {
                @Override
                public Maybe<EVALUATED_VALUE> performMonadicOperation(final EVALUATED_VALUE leftBranchValueOfSyntaxTreeToEvaluate) {
                    return _maybeEvaluateMaybeSyntaxTree(syntaxTreeToEvaluate.maybeGetRightChildTree()).applyGivenOperationOntoThisObjectMondically(new MonadicOperation<Monad<EVALUATED_VALUE>, EVALUATED_VALUE, EVALUATED_VALUE>() {
                        @Override
                        public Maybe<EVALUATED_VALUE> performMonadicOperation(EVALUATED_VALUE rightBranchValueOfSyntaxTreeToEvaluate) {
                            return _evaluationRuleset.maybeValueResultOfApplyingInfixTokenToLeftAndRightValue(syntaxTreeToEvaluate.getRootToken(), leftBranchValueOfSyntaxTreeToEvaluate, rightBranchValueOfSyntaxTreeToEvaluate);
                        }
                    });
                }
            });

        } else {
            return Maybe.asNothing();
        }
    }

    private Maybe<EVALUATED_VALUE> _maybeEvaluateMaybeSyntaxTree(final Maybe<SyntaxTree> maybeSyntaxTreeToEvaluate) {
        return maybeSyntaxTreeToEvaluate.applyGivenOperationOntoThisObjectMondically(new MonadicOperation<Monad<EVALUATED_VALUE>, SyntaxTree, EVALUATED_VALUE>() {
            @Override
            public Maybe<EVALUATED_VALUE> performMonadicOperation(SyntaxTree syntaxTree) {
                return _maybeEvaluateSyntaxTree(syntaxTree);
            }
        });
    }
}
