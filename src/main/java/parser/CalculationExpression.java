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
            System.out.println("evaluateDoubleResultOfCalculationExpressionWithRuleset: " + calculationExpressionSyntaxTree.DEBUG_getStringOfTree());
            SyntaxTreeEvaluator<CalculationValue> evaluatorOfCalculationExpression = SyntaxTreeEvaluator.syntaxTreeEvaluatorWithEvaluationRuleset(calculationExpressionRuleset.getEvaluationRulesetForCalculationExpressions());

            CalculationValue resultOfCalculationExpression = evaluatorOfCalculationExpression.evaluateSyntaxTree(calculationExpressionSyntaxTree);

            Maybe<Double> maybeDoubleResultOfCalculationExpression = resultOfCalculationExpression.maybeValueAsDouble();
            if (maybeDoubleResultOfCalculationExpression.isNotNothing()) {
                return _roundToEaightDecimalPlaces(maybeDoubleResultOfCalculationExpression.object());
            } else {
                throw new CalculationExpressionException("resulting value was not a double, possibly incomplete expression");
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            throw new CalculationExpressionException(exception.getMessage());
        }
    }

    private Double _roundToEaightDecimalPlaces(Double doubleToReound) {
        Double roundingBase = 100000000.0;
        return Math.round(doubleToReound * roundingBase) / roundingBase;
    }


    public static CalculationExpressionRuleset GENERAL_CALCULATION_RULESET() {
        ArrayList<Operation<CalculationValue>> operationsInRuleset = new ArrayList<>();

        operationsInRuleset.add(Operations.Constants.PI_CONSTANT);
        operationsInRuleset.add(Operations.ADDITION_OPERATION);
        operationsInRuleset.add(Operations.SUBTRACTION_OPERATION);
        operationsInRuleset.add(Operations.MULTIPLICATION_OPERATION);
        operationsInRuleset.add(Operations.DIVISION_OPERATION);
        operationsInRuleset.add(Operations.MODULO_OPERATION);
        operationsInRuleset.add(Operations.SIN_OPERATION);
        operationsInRuleset.add(Operations.COS_OPERATION);
        operationsInRuleset.add(Operations.TAN_OPERATION);
        operationsInRuleset.add(Operations.ARCCOS_OPERATION);
        operationsInRuleset.add(Operations.ARCSIN_OPERATION);
        operationsInRuleset.add(Operations.ARCTAN_OPERATION);
        operationsInRuleset.add(Operations.COT_OPERATION);
        operationsInRuleset.add(Operations.CSC_OPERATION);
        operationsInRuleset.add(Operations.SEC_OPERATION);
        operationsInRuleset.add(Operations.ABS_OPERATION);
        operationsInRuleset.add(Operations.LOG_OPERATION);
        operationsInRuleset.add(Operations.POW_OPERATION);
        operationsInRuleset.add(Operations.NTHROOT_OPERATION);

        //add constant PI
        return CalculationExpressionRuleset.newRulesetParsingValuesAndGroupingWithBeginGroupSyntaxAndEndGroupSyntaxAndSequenceSeperatorSyntaxAndOperations(
                Syntax.atomicSyntaxAsCharacter('('),
                Syntax.atomicSyntaxAsCharacter(')'),
                Syntax.atomicSyntaxAsCharacter(','),
                operationsInRuleset
        );
    }



    public static Double evaluateStringExpression(String stringExpression) throws CalculationExpressionException {
        return CalculationExpression.calculationExpressionWithString(stringExpression)
                .evaluateDoubleResultOfCalculationExpressionWithRuleset(CalculationExpression.GENERAL_CALCULATION_RULESET());
    }
}
