package parser;

import parser.functional.*;
import parser.evaluator.EvaluationRuleset;
import parser.syntax_tree.SyntaxRuleset;
import parser.tokeniser.AcceptedAtomicToken;
import parser.tokeniser.TokeniserRuleset;
import parser.tokeniser.tokens.NumericalToken;
import parser.tokeniser.tokens.Token;

import java.util.ArrayList;
import java.util.List;

public class CalculationExpressionRuleset {
    private final Syntax _beginGroupSyntax;
    private final Syntax _endGroupSyntax;
    private final Syntax _sequenceSeperatorSyntax;
    private final List<Operation<CalculationValue>> _operations;

    private static int BINDING_STREGTH_OF_SEQUENCE_SEPERATOR = 0;

    public static CalculationExpressionRuleset newRulesetParsingValuesAndGroupingWithBeginGroupSyntaxAndEndGroupSyntaxAndSequenceSeperatorSyntaxAndOperations(Syntax beginGroupSyntax, Syntax endGroupSyntax, Syntax sequenceSeperatorSyntax, List<Operation<CalculationValue>> operations) {
        return new CalculationExpressionRuleset(beginGroupSyntax, endGroupSyntax, sequenceSeperatorSyntax, operations);
    }

    private CalculationExpressionRuleset(Syntax beginGroupSyntax, Syntax endGroupSyntax, Syntax sequenceSeperatorSyntax, List<Operation<CalculationValue>> operations) {
        _beginGroupSyntax = beginGroupSyntax;
        _endGroupSyntax = endGroupSyntax;
        _operations = operations;
        _sequenceSeperatorSyntax = sequenceSeperatorSyntax;
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
                for (Operation<CalculationValue> operation : _operations) {
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
                if (_sequenceSeperatorSyntax.maybeGetAcceptedAtomicTokenRepresentation().isNotNothing()) {
                    acceptedAtomicTokensInPriorityOrder.add(_sequenceSeperatorSyntax.maybeGetAcceptedAtomicTokenRepresentation().object());
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
                } else if (tokenToClassify.isIdenticalToToken(_sequenceSeperatorSyntax.getTokenRepresentation())) {
                    syntaxCategoryClassifier.classfiyAsInfixOperationWithBindingStrength(BINDING_STREGTH_OF_SEQUENCE_SEPERATOR);
                } else if (tokenToClassify instanceof NumericalToken) {
                    syntaxCategoryClassifier.classifyAsValue();
                } else {
                    Maybe<Operation<CalculationValue>> maybeOperationMatchingTokenToClassify = _maybeMatchTokenToOperation(tokenToClassify);
                    if (maybeOperationMatchingTokenToClassify.isNotNothing()) {
                        maybeOperationMatchingTokenToClassify.object().classifySyntaxOfOperation(syntaxCategoryClassifier);
                    } else {
                        syntaxCategoryClassifier.classifyAsInvalid();
                    }
                }
            }
        };
    }

    private Maybe<Operation<CalculationValue>> _maybeMatchTokenToOperation(Token givenToken) {
        for (Operation<CalculationValue> operation : _operations) {
            boolean tokenToClassifyMatchesSyntaxOfOperation = operation.getSyntaxOfOperation().getTokenRepresentation().isIdenticalToToken(givenToken);
            if (tokenToClassifyMatchesSyntaxOfOperation) {
                return Maybe.asObject(operation);
            }
        }
        return Maybe.asNothing();
    }

    public EvaluationRuleset<CalculationValue> getEvaluationRulesetForCalculationExpressions() {
        return new EvaluationRuleset<CalculationValue>() {
            @Override
            public Maybe<CalculationValue> maybeValueRepresentationOfValueToken(Token givenToken) {
                if (givenToken instanceof NumericalToken) {
                    return Maybe.asObject(CalculationValue.calculationValueAsDouble(((NumericalToken)givenToken).getDoubleRepresentationOfNumericalToken()));
                } else {
                    Maybe<Operation<CalculationValue>> maybeOperationForGivenToken = _maybeMatchTokenToOperation(givenToken);
                    if (maybeOperationForGivenToken.isNotNothing()) {
                        return maybeOperationForGivenToken.object().maybeValueResultOfEvaluatingAsConstantToValue();
                    }
                    return Maybe.asNothing();
                }
            }
            @Override
            public Maybe<CalculationValue> maybeValueResultOfApplyingPrefixTokenToValue(Token givenPrefixToken, final CalculationValue givenValue) {
                return _maybeMatchTokenToOperation(givenPrefixToken).applyGivenOperationOntoThisObjectMondically(new MonadicOperation<Monad<CalculationValue>, Operation<CalculationValue>, CalculationValue>() {
                    @Override
                    public Maybe<CalculationValue> performMonadicOperation(Operation<CalculationValue> operationCorrespondingToGivenPrefixToken) {
                        return operationCorrespondingToGivenPrefixToken.maybeValueResultOfApplyingAsPrefixOperationToValue(givenValue);
                    }
                });
            }
            @Override
            public Maybe<CalculationValue> maybeValueResultOfApplyingInfixTokenToLeftAndRightValue(Token givenInfixToken, CalculationValue givenLeftValue, CalculationValue givenRightValue) {
                if (givenInfixToken.isIdenticalToToken(_sequenceSeperatorSyntax.getTokenRepresentation())) {
                    return Maybe.asObject(CalculationValue.calculationValueByConcatenatingValueToValue(givenLeftValue, givenRightValue));
                } else {
                    return _maybeMatchTokenToOperation(givenInfixToken).applyGivenOperationOntoThisObjectMondically(new MonadicOperation<Monad<CalculationValue>, Operation<CalculationValue>, CalculationValue>() {
                        @Override
                        public Maybe<CalculationValue> performMonadicOperation(Operation<CalculationValue> operationCorrespondingToGivenInfixToken) {
                            return operationCorrespondingToGivenInfixToken.maybeValueResultOfApplyingAsInfixOperationToLeftAndRightValue(givenLeftValue, givenRightValue);
                        }
                    });
                }
            }
        };
    }
}
