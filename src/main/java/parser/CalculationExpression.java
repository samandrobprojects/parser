package parser;

import functional.Maybe;
import parser.evaluator.EvaluationRuleset;
import parser.evaluator.SyntaxTreeEvaluator;
import parser.syntax_tree.SyntaxRuleset;
import parser.syntax_tree.SyntaxTree;
import parser.syntax_tree.SyntaxTreeBuilder;
import parser.tokeniser.AcceptedAtomicToken;
import parser.tokeniser.Tokeniser;
import parser.tokeniser.TokeniserRuleset;
import parser.tokeniser.tokens.AtomicToken;
import parser.tokeniser.tokens.Token;

import java.util.ArrayList;
import java.util.List;

public class CalculationExpression {
    private String _calculationExpressionString;

    public static CalculationExpression calculationExpressionWithString(String calculationExpressionString) {
        return new CalculationExpression(calculationExpressionString);
    }

    private CalculationExpression(String calculationExpressionString) {
        _calculationExpressionString = calculationExpressionString;
    }

    public double evaluateDoubleResultOfCalculationExpressionWithRuleset(CalculationExpressionRuleset calculationExpressionRuleset) throws CalculationExpressionException {
        try {
            Tokeniser tokeniser = Tokeniser.createTokeniserWithTokeniserRuleset(calculationExpressionRuleset.getTokeniserRulesetForCalculationExpressions());
            List<Token> tokenisedCalculationExpression = tokeniser.tokeniseStringIntoListOfTokens(_calculationExpressionString);
            SyntaxTreeBuilder syntaxTreeBuilder = SyntaxTreeBuilder.syntaxTreeBuilderWithSyntaxRuleset(calculationExpressionRuleset.getSyntaxRulesetForCalculationExpressions());
            SyntaxTree calculationExpressionSyntaxTree = syntaxTreeBuilder.buildSyntaxTreeFromListOfTokens(tokenisedCalculationExpression);
            SyntaxTreeEvaluator<Double> evaluatorOfCalculationExpression = SyntaxTreeEvaluator.syntaxTreeEvaluatorWithEvaluationRuleset(calculationExpressionRuleset.getEvaluationRulesetForCalculationExpressions());
            Double resultOfCalculationExpression = evaluatorOfCalculationExpression.evaluateSyntaxTree(calculationExpressionSyntaxTree);
            return resultOfCalculationExpression;
        } catch (Exception exception) {
            throw new CalculationExpressionException(exception.getLocalizedMessage());
        }
    }


    public static CalculationExpressionRuleset calculationRulesetTest() {
        ArrayList<Operation<Double>> operationsInRuleset = new ArrayList<>();

        operationsInRuleset.add(Operation.infixOperationWithSyntaxAndBindingStrengthAndInfixOperationEvaluation(Syntax.atomicSyntaxAsCharacter('+'), 50, new Operation.InfixOperationEvaluation<Double>() {
            @Override
            public Maybe<Double> maybeValueResultOfApplyingInfixOperationToLeftAndRightValue(Double givenLeftValue, Double givenRightValue) {
                return Maybe.asObject(givenLeftValue + givenRightValue);
            }
        }));

        operationsInRuleset.add(Operation.infixOperationWithSyntaxAndBindingStrengthAndInfixOperationEvaluation(Syntax.atomicSyntaxAsCharacter('/'), 100, new Operation.InfixOperationEvaluation<Double>() {
            @Override
            public Maybe<Double> maybeValueResultOfApplyingInfixOperationToLeftAndRightValue(Double givenLeftValue, Double givenRightValue) {
                if (givenRightValue == 0) {
                    return Maybe.asNothing();
                } else {
                    return Maybe.asObject(givenLeftValue / givenRightValue);
                }
            }
        }));

        operationsInRuleset.add(Operation.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("cos"), 200, new Operation.PrefixOperationEvaluation<Double>() {
            @Override
            public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double givenValue) {
                return Maybe.asObject(Math.cos(givenValue));
            }
        }));

        return CalculationExpressionRuleset.newRulesetParsingValuesAndGroupingWithBeginGroupSyntaxAndEndGroupSyntaxAndOperations(
                Syntax.atomicSyntaxAsCharacter('('),
                Syntax.atomicSyntaxAsCharacter(')'),
                operationsInRuleset
        );
    }
}
