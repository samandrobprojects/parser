package parser;

import functional.Maybe;
import functional.Monad;
import functional.MonadicOperation;
import parser.evaluator.EvaluationRuleset;
import parser.syntax_tree.SyntaxRuleset;
import parser.tokeniser.AcceptedAtomicToken;
import parser.tokeniser.TokeniserRuleset;
import parser.tokeniser.tokens.AtomicToken;
import parser.tokeniser.tokens.IdentifierToken;
import parser.tokeniser.tokens.NumericalToken;
import parser.tokeniser.tokens.Token;

import java.util.ArrayList;
import java.util.List;

public class CalculationExpressionRuleset {
    private final Syntax _beginGroupSyntax;
    private final Syntax _endGroupSyntax;
    private final List<Operation<Double>> _operations;

    public static CalculationExpressionRuleset newRulesetParsingValuesAndGroupingWithBeginGroupSyntaxAndEndGroupSyntaxAndOperations(Syntax beginGroupSyntax, Syntax endGroupSyntax, List<Operation<Double>> operations) {
        return new CalculationExpressionRuleset(beginGroupSyntax, endGroupSyntax, operations);
    }

    private CalculationExpressionRuleset(Syntax beginGroupSyntax, Syntax endGroupSyntax, List<Operation<Double>> operations) {
        _beginGroupSyntax = beginGroupSyntax;
        _endGroupSyntax = endGroupSyntax;
        _operations = operations;
    }


    public TokeniserRuleset getTokeniserRulesetForCalculationExpressions() {
        return new TokeniserRuleset() {
            @Override
            public boolean acceptNumericalTokens() {
                return true;
            }
            @Override
            public boolean acceptIdentifierTokens() {
                return true;
            }
            @Override
            public List<AcceptedAtomicToken> listOfAcceptedAtomicTokensInPriorityOrder() {
                ArrayList<AcceptedAtomicToken> acceptedAtomicTokensInPriorityOrder = new ArrayList<>();
                for (Operation<Double> operation : _operations) {
                    Maybe<AcceptedAtomicToken> maybeAtomicTokenRepresentationOfOperation = operation.getSyntaxOfOperation().maybeGetAcceptedAtomicTokenRepresentation();
                    if (maybeAtomicTokenRepresentationOfOperation.isNotNothing()) {
                        acceptedAtomicTokensInPriorityOrder.add(maybeAtomicTokenRepresentationOfOperation.object());
                    }
                }
                if (_beginGroupSyntax.maybeGetAcceptedAtomicTokenRepresentation().isNotNothing()) {
                    acceptedAtomicTokensInPriorityOrder.add(_beginGroupSyntax.maybeGetAcceptedAtomicTokenRepresentation().object());
                }
                if (_endGroupSyntax.maybeGetAcceptedAtomicTokenRepresentation().isNotNothing()) {
                    acceptedAtomicTokensInPriorityOrder.add(_endGroupSyntax.maybeGetAcceptedAtomicTokenRepresentation().object());
                }
                return acceptedAtomicTokensInPriorityOrder;
            }
        };
    }

    public SyntaxRuleset getSyntaxRulesetForCalculationExpressions() {
        return new SyntaxRuleset() {
            @Override
            public void classifyTokenIntoSyntaxCategoryWithClassifier(Token tokenToClassify, SyntaxCategoryClassifier syntaxCategoryClassifier) {
                if (tokenToClassify.isIdenticalToToken(_beginGroupSyntax.getTokenRepresentation())) {
                    syntaxCategoryClassifier.classfiyAsBeginGrouping();
                } else if (tokenToClassify.isIdenticalToToken(_endGroupSyntax.getTokenRepresentation())) {
                    syntaxCategoryClassifier.classfiyAsEndGrouping();
                } else if (tokenToClassify instanceof NumericalToken) {
                    syntaxCategoryClassifier.classifyAsValue();
                } else {
                    Maybe<Operation<Double>> maybeOperationMatchingTokenToClassify = _maybeMatchTokenToOperation(tokenToClassify);
                    if (maybeOperationMatchingTokenToClassify.isNotNothing()) {
                        maybeOperationMatchingTokenToClassify.object().classifySyntaxOfOperation(syntaxCategoryClassifier);
                    } else {
                        syntaxCategoryClassifier.classifyAsInvalid();
                    }
                }
            }
        };
    }

    private Maybe<Operation<Double>> _maybeMatchTokenToOperation(Token givenToken) {
        for (Operation<Double> operation : _operations) {
            boolean tokenToClassifyMatchesSyntaxOfOperation = operation.getSyntaxOfOperation().getTokenRepresentation().isIdenticalToToken(givenToken);
            if (tokenToClassifyMatchesSyntaxOfOperation) {
                return Maybe.asObject(operation);
            }
        }
        return Maybe.asNothing();
    }

    public EvaluationRuleset<Double> getEvaluationRulesetForCalculationExpressions() {
        return new EvaluationRuleset<Double>() {
            @Override
            public Maybe<Double> maybeValueRepresentationOfValueToken(Token givenToken) {
                if (givenToken instanceof NumericalToken) {
                    return Maybe.asObject(((NumericalToken)givenToken).getDoubleRepresentationOfNumericalToken());
                } else {
                    Maybe<Operation<Double>> maybeOperationForGivenToken = _maybeMatchTokenToOperation(givenToken);
                    if (maybeOperationForGivenToken.isNotNothing()) {
                        return maybeOperationForGivenToken.object().maybeValueResultOfEvaluatingAsConstantToValue();
                    }
                    return Maybe.asNothing();
                }
            }
            @Override
            public Maybe<Double> maybeValueResultOfApplyingPrefixTokenToValue(Token givenPrefixToken, final Double givenValue) {
                return _maybeMatchTokenToOperation(givenPrefixToken).applyGivenOperationOntoThisObjectMondically(new MonadicOperation<Monad<Double>, Operation<Double>, Double>() {
                    @Override
                    public Maybe<Double> performMonadicOperation(Operation<Double> operationCorrespondingToGivenPrefixToken) {
                        return operationCorrespondingToGivenPrefixToken.maybeValueResultOfApplyingAsPrefixOperationToValue(givenValue);
                    }
                });
            }
            @Override
            public Maybe<Double> maybeValueResultOfApplyingInfixTokenToLeftAndRightValue(Token givenInfixToken, Double givenLeftValue, Double givenRightValue) {
                return _maybeMatchTokenToOperation(givenInfixToken).applyGivenOperationOntoThisObjectMondically(new MonadicOperation<Monad<Double>, Operation<Double>, Double>() {
                    @Override
                    public Maybe<Double> performMonadicOperation(Operation<Double> operationCorrespondingToGivenInfixToken) {
                        return operationCorrespondingToGivenInfixToken.maybeValueResultOfApplyingAsInfixOperationToLeftAndRightValue(givenLeftValue, givenRightValue);
                    }
                });
            }
        };
    }


}
