package parser;

import functional.Maybe;

public class Operations {

    public static final Operation<Double> ADDITION_OPERATION = Operation.infixOperationWithSyntaxAndBindingStrengthAndInfixOperationEvaluation(Syntax.atomicSyntaxAsCharacter('+'), 50, new Operation.InfixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingInfixOperationToLeftAndRightValue(Double givenLeftValue, Double givenRightValue) {
            return Maybe.asObject(givenLeftValue + givenRightValue);
        }
    });

    public static final Operation<Double> SUBTRACTION_OPERATION = Operation.infixOperationWithSyntaxAndBindingStrengthAndInfixOperationEvaluation(Syntax.atomicSyntaxAsCharacter('-'), 50, new Operation.InfixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingInfixOperationToLeftAndRightValue(Double givenLeftValue, Double givenRightValue) {
            return Maybe.asObject(givenLeftValue - givenRightValue);
        }
    });

    public static final Operation<Double> MULTIPLICATION_OPERATION = Operation.infixOperationWithSyntaxAndBindingStrengthAndInfixOperationEvaluation(Syntax.atomicSyntaxAsCharacter('*'), 100, new Operation.InfixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingInfixOperationToLeftAndRightValue(Double givenLeftValue, Double givenRightValue) {
            return Maybe.asObject(givenLeftValue * givenRightValue);
        }
    });

    public static final Operation<Double> DIVISION_OPERATION = Operation.infixOperationWithSyntaxAndBindingStrengthAndInfixOperationEvaluation(Syntax.atomicSyntaxAsCharacter('/'), 100, new Operation.InfixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingInfixOperationToLeftAndRightValue(Double givenLeftValue, Double givenRightValue) {
            if (givenRightValue == 0) {
                return Maybe.asNothing();
            } else {
                return Maybe.asObject(givenLeftValue / givenRightValue);
            }
        }
    });

    public static final Operation<Double> MODULO_OPERATION = Operation.infixOperationWithSyntaxAndBindingStrengthAndInfixOperationEvaluation(Syntax.atomicSyntaxAsCharacter('%'), 100, new Operation.InfixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingInfixOperationToLeftAndRightValue(Double givenLeftValue, Double givenRightValue) {
            if (givenRightValue == 0) {
                return Maybe.asNothing();
            } else {
                return Maybe.asObject(givenLeftValue % givenRightValue);
            }
        }
    });

    public static final Operation<Double> SIN_OPERATION = Operation.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("sin"), 200, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperation) {
            return Maybe.asObject(Math.sin(valueToApplyOperation));
        }
    });

    public static final Operation<Double> COS_OPERATION = Operation.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("cos"), 200, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperation) {
            return Maybe.asObject(Math.cos(valueToApplyOperation));
        }
    });

    public static final Operation<Double> TAN_OPERATION = Operation.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("tan"), 200, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperation) {
            return Maybe.asObject(Math.tan(valueToApplyOperation));
        }
    });

    public static final Operation<Double> COT_OPERATION = Operation.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("cot"), 200, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperation) {
            Double tangentOfValue = Math.tan(valueToApplyOperation);
            if (tangentOfValue != 0.0) {
                return Maybe.asObject(1.0 / tangentOfValue);
            } else {
                return Maybe.asNothing();
            }
        }
    });

    public static final Operation<Double> SEC_OPERATION = Operation.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("sec"), 200, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperation) {
            Double sinOfValue = Math.sin(valueToApplyOperation);
            if (sinOfValue != 0.0) {
                return Maybe.asObject(1.0 / sinOfValue);
            } else {
                return Maybe.asNothing();
            }
        }
    });

    public static final Operation<Double> CSC_OPERATION = Operation.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("csc"), 200, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperation) {
            Double cosOfValue = Math.cos(valueToApplyOperation);
            if (cosOfValue != 0.0) {
                return Maybe.asObject(1.0 / cosOfValue);
            } else {
                return Maybe.asNothing();
            }
        }
    });

    public static final Operation<Double> ARCTAN_OPERATION = Operation.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("arctan"), 200, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperation) {
            return Maybe.asObject(Math.atan(valueToApplyOperation));
        }
    });

    public static final Operation<Double> ARCSIN_OPERATION = Operation.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("arcsin"), 200, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperation) {
            return Maybe.asObject(Math.asin(valueToApplyOperation));
        }
    });

    public static final Operation<Double> ARCCOS_OPERATION = Operation.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("arccos"), 200, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperation) {
            return Maybe.asObject(Math.acos(valueToApplyOperation));
        }
    });

    public static final Operation<Double> ABS_OPERATION = Operation.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("abs"), 200, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperation) {
            return Maybe.asObject(Math.abs(valueToApplyOperation));
        }
    });

    public static final Operation<Double> LOG_OPERATION = Operation.infixOperationWithSyntaxAndBindingStrengthAndInfixOperationEvaluation(Syntax.atomicSyntaxAsCharacter('$'), 250, new Operation.InfixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingInfixOperationToLeftAndRightValue(Double givenLeftValue, Double givenRightValue) {
            return Maybe.asObject(Math.log(givenRightValue) / Math.log(givenLeftValue));
        }
    });

    public static final Operation<Double> POWER_OPERATION = Operation.infixOperationWithSyntaxAndBindingStrengthAndInfixOperationEvaluation(Syntax.atomicSyntaxAsCharacter('^'), 250, new Operation.InfixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingInfixOperationToLeftAndRightValue(Double givenLeftValue, Double givenRightValue) {
            return Maybe.asObject(Math.pow(givenLeftValue, givenRightValue));
        }
    });

    public static final Operation<Double> ROOT_OPERATION = Operation.infixOperationWithSyntaxAndBindingStrengthAndInfixOperationEvaluation(Syntax.atomicSyntaxAsCharacter('#'), 250, new Operation.InfixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingInfixOperationToLeftAndRightValue(Double givenLeftValue, Double givenRightValue) {
            return Maybe.asObject(Math.pow(givenRightValue, 1.0 / givenLeftValue));
        }
    });
}
