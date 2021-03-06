package parser;

import parser.functional.*;
import parser.evaluator.SyntaxTreeEvaluator;
import parser.syntax_tree.SyntaxTree;
import parser.syntax_tree.SyntaxTreeBuilder;
import parser.tokeniser.Tokeniser;
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
            SyntaxTreeEvaluator<CalculationValue> evaluatorOfCalculationExpression = SyntaxTreeEvaluator.syntaxTreeEvaluatorWithEvaluationRuleset(calculationExpressionRuleset.getEvaluationRulesetForCalculationExpressions());

            CalculationValue resultOfCalculationExpression = evaluatorOfCalculationExpression.evaluateSyntaxTree(calculationExpressionSyntaxTree);

            Maybe<Double> maybeDoubleResultOfCalculationExpression = resultOfCalculationExpression.maybeValueAsDouble();
            if (maybeDoubleResultOfCalculationExpression.isNotNothing()) {
                return maybeDoubleResultOfCalculationExpression.object();
            } else {
                throw new CalculationExpressionException("resulting value was not a double, possibly incomplete expression");
            }

        } catch (Exception exception) {
            throw new CalculationExpressionException(exception.getMessage());
        }
    }

    private Double _roundToEightDecimalPlaces(Double doubleToReound) {
        Double roundingBase = 100000000.0;
        return Math.round(doubleToReound * roundingBase) / roundingBase;
    }

    public static CalculationExpressionRuleset GENERAL_CALCULATION_RULESET_USING_RADIANS() {
        ArrayList<Operation<CalculationValue>> operationsInRuleset = new ArrayList<>();

        operationsInRuleset.add(Operations.Constants.PI_CONSTANT);
        operationsInRuleset.add(Operations.ADDITION_OPERATION);
        operationsInRuleset.add(Operations.SUBTRACTION_OPERATION);
        operationsInRuleset.add(Operations.MULTIPLICATION_OPERATION);
        operationsInRuleset.add(Operations.DIVISION_OPERATION);
        operationsInRuleset.add(Operations.MODULO_OPERATION);
        operationsInRuleset.add(Operations.SIN_OPERATION_IN_RADIANS);
        operationsInRuleset.add(Operations.COS_OPERATION_IN_RADIANS);
        operationsInRuleset.add(Operations.TAN_OPERATION_IN_RADIANS);
        operationsInRuleset.add(Operations.ARCCOS_OPERATION_IN_RADIANS);
        operationsInRuleset.add(Operations.ARCSIN_OPERATION_IN_RADIANS);
        operationsInRuleset.add(Operations.ARCTAN_OPERATION_IN_RADIANS);
        operationsInRuleset.add(Operations.COT_OPERATION_IN_RADIANS);
        operationsInRuleset.add(Operations.CSC_OPERATION_IN_RADIANS);
        operationsInRuleset.add(Operations.SEC_OPERATION_IN_RADIANS);
        operationsInRuleset.add(Operations.ABS_OPERATION);
        operationsInRuleset.add(Operations.LOG_OPERATION);
        operationsInRuleset.add(Operations.POW_OPERATION);
        operationsInRuleset.add(Operations.NTHROOT_OPERATION);
        operationsInRuleset.add(Operations.MEAN_OPERATION);
        operationsInRuleset.add(Operations.MEDIAN_OPERATION);
        operationsInRuleset.add(Operations.STD_OPERATION);

        //add constant PI
        return CalculationExpressionRuleset.newRulesetParsingValuesAndGroupingWithBeginGroupSyntaxAndEndGroupSyntaxAndSequenceSeperatorSyntaxAndOperations(
                Syntax.atomicSyntaxAsCharacter('('),
                Syntax.atomicSyntaxAsCharacter(')'),
                Syntax.atomicSyntaxAsCharacter(','),
                operationsInRuleset
        );
    }

    public static CalculationExpressionRuleset GENERAL_CALCULATION_RULESET_USING_DEGREES() {
        ArrayList<Operation<CalculationValue>> operationsInRuleset = new ArrayList<>();

        operationsInRuleset.add(Operations.Constants.PI_CONSTANT);
        operationsInRuleset.add(Operations.ADDITION_OPERATION);
        operationsInRuleset.add(Operations.SUBTRACTION_OPERATION);
        operationsInRuleset.add(Operations.MULTIPLICATION_OPERATION);
        operationsInRuleset.add(Operations.DIVISION_OPERATION);
        operationsInRuleset.add(Operations.MODULO_OPERATION);
        operationsInRuleset.add(Operations.SIN_OPERATION_IN_DEGREES);
        operationsInRuleset.add(Operations.COS_OPERATION_IN_DEGREES);
        operationsInRuleset.add(Operations.TAN_OPERATION_IN_DEGREES);
        operationsInRuleset.add(Operations.ARCCOS_OPERATION_IN_DEGREES);
        operationsInRuleset.add(Operations.ARCSIN_OPERATION_IN_DEGREES);
        operationsInRuleset.add(Operations.ARCTAN_OPERATION_IN_DEGREES);
        operationsInRuleset.add(Operations.COT_OPERATION_IN_DEGREES);
        operationsInRuleset.add(Operations.CSC_OPERATION_IN_DEGREES);
        operationsInRuleset.add(Operations.SEC_OPERATION_IN_DEGREES);
        operationsInRuleset.add(Operations.ABS_OPERATION);
        operationsInRuleset.add(Operations.LOG_OPERATION);
        operationsInRuleset.add(Operations.POW_OPERATION);
        operationsInRuleset.add(Operations.NTHROOT_OPERATION);
        operationsInRuleset.add(Operations.MEAN_OPERATION);
        operationsInRuleset.add(Operations.MEDIAN_OPERATION);
        operationsInRuleset.add(Operations.STD_OPERATION);

        //add constant PI
        return CalculationExpressionRuleset.newRulesetParsingValuesAndGroupingWithBeginGroupSyntaxAndEndGroupSyntaxAndSequenceSeperatorSyntaxAndOperations(
                Syntax.atomicSyntaxAsCharacter('('),
                Syntax.atomicSyntaxAsCharacter(')'),
                Syntax.atomicSyntaxAsCharacter(','),
                operationsInRuleset
        );
    }

    public static Double evaluateStringExpressionUsingRadians(String stringExpression) throws CalculationExpressionException {
        return CalculationExpression.calculationExpressionWithString(stringExpression)
                .evaluateDoubleResultOfCalculationExpressionWithRuleset(CalculationExpression.GENERAL_CALCULATION_RULESET_USING_RADIANS());
    }

    public static Double evaluateStringExpressionUsingDegrees(String stringExpression) throws CalculationExpressionException {
        return CalculationExpression.calculationExpressionWithString(stringExpression)
                .evaluateDoubleResultOfCalculationExpressionWithRuleset(CalculationExpression.GENERAL_CALCULATION_RULESET_USING_DEGREES());
    }
}
