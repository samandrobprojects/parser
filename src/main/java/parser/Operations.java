package parser;

import functional.Maybe;
import functional.Monad;
import functional.MonadicOperation;

import java.util.List;

public class Operations {



    public static class Constants {

        public static final Operation<CalculationValue> PI_CONSTANT = Operations.constantOperationWithSyntaxConstantOperationEvaluation(Syntax.identifierSyntaxAsString("PI"), new Operation.ConstantOperationEvaluation<Double>() {
            @Override
            public Maybe<Double> maybeValueResultOfEvaluatingConstantToValue() {
                return Maybe.asObject(Math.PI);
            }
        });

    }

    public static final Operation<CalculationValue> ADDITION_OPERATION = Operations.infixOperationWithSyntaxAndBindingStrengthAndInfixOperationEvaluation(Syntax.atomicSyntaxAsCharacter('+'), 50, new Operation.InfixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingInfixOperationToLeftAndRightValue(Double givenLeftValue, Double givenRightValue) {
            return Maybe.asObject(givenLeftValue + givenRightValue);
        }
    });

    public static final Operation<CalculationValue> SUBTRACTION_OPERATION = Operations.infixOperationWithSyntaxAndBindingStrengthAndInfixOperationEvaluation(Syntax.atomicSyntaxAsCharacter('-'), 50, new Operation.InfixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingInfixOperationToLeftAndRightValue(Double givenLeftValue, Double givenRightValue) {
            return Maybe.asObject(givenLeftValue - givenRightValue);
        }
    });

    public static final Operation<CalculationValue> MULTIPLICATION_OPERATION = Operations.infixOperationWithSyntaxAndBindingStrengthAndInfixOperationEvaluation(Syntax.atomicSyntaxAsCharacter('*'), 100, new Operation.InfixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingInfixOperationToLeftAndRightValue(Double givenLeftValue, Double givenRightValue) {
            return Maybe.asObject(givenLeftValue * givenRightValue);
        }
    });

    public static final Operation<CalculationValue> DIVISION_OPERATION = Operations.infixOperationWithSyntaxAndBindingStrengthAndInfixOperationEvaluation(Syntax.atomicSyntaxAsCharacter('/'), 100, new Operation.InfixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingInfixOperationToLeftAndRightValue(Double givenLeftValue, Double givenRightValue) {
            if (givenRightValue == 0) {
                return Maybe.asNothing();
            } else {
                return Maybe.asObject(givenLeftValue / givenRightValue);
            }
        }
    });

    public static final Operation<CalculationValue> MODULO_OPERATION = Operations.infixOperationWithSyntaxAndBindingStrengthAndInfixOperationEvaluation(Syntax.atomicSyntaxAsCharacter('%'), 100, new Operation.InfixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingInfixOperationToLeftAndRightValue(Double givenLeftValue, Double givenRightValue) {
            if (givenRightValue == 0) {
                return Maybe.asNothing();
            } else {
                return Maybe.asObject(givenLeftValue % givenRightValue);
            }
        }
    });

    public static final Operation<CalculationValue> SIN_OPERATION = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("sin"), 200, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperation) {
            return Maybe.asObject(Math.sin(valueToApplyOperation));
        }
    });

    public static final Operation<CalculationValue> COS_OPERATION = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("cos"), 200, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperation) {
            return Maybe.asObject(Math.cos(valueToApplyOperation));
        }
    });

    public static final Operation<CalculationValue> TAN_OPERATION = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("tan"), 200, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperation) {
            return Maybe.asObject(Math.tan(valueToApplyOperation));
        }
    });

    public static final Operation<CalculationValue> COT_OPERATION = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("cot"), 200, new Operation.PrefixOperationEvaluation<Double>() {
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

    public static final Operation<CalculationValue> SEC_OPERATION = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("sec"), 200, new Operation.PrefixOperationEvaluation<Double>() {
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

    public static final Operation<CalculationValue> CSC_OPERATION = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("csc"), 200, new Operation.PrefixOperationEvaluation<Double>() {
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

    public static final Operation<CalculationValue> ARCTAN_OPERATION = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("arctan"), 200, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperation) {
            return Maybe.asObject(Math.atan(valueToApplyOperation));
        }
    });

    public static final Operation<CalculationValue> ARCSIN_OPERATION = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("arcsin"), 200, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperation) {
            return Maybe.asObject(Math.asin(valueToApplyOperation));
        }
    });

    public static final Operation<CalculationValue> ARCCOS_OPERATION = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("arccos"), 200, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperation) {
            return Maybe.asObject(Math.acos(valueToApplyOperation));
        }
    });

    public static final Operation<CalculationValue> ABS_OPERATION = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("abs"), 200, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperation) {
            return Maybe.asObject(Math.abs(valueToApplyOperation));
        }
    });

    public static final Operation<CalculationValue> LOG_OPERATION = Operations.functionOperationWithSyntaxAndBindingStrengthAndNumberOfArgumentsAndFunctionOperationEvaluation(
            Syntax.identifierSyntaxAsString("log"), 250, 2, new FunctionOperationEvaluation<Double>() {
                @Override
                public Maybe<Double> maybeValueResultOfApplyingFunctionOperationToListOfArguments(List<Double> givenArgumentList) {
                    Double x = givenArgumentList.get(0);
                    Double base = givenArgumentList.get(1);
                    return Maybe.asObject(Math.log(x) / Math.log(base));
                }
            }
    );

    public static final Operation<CalculationValue> POW_OPERATION = Operations.functionOperationWithSyntaxAndBindingStrengthAndNumberOfArgumentsAndFunctionOperationEvaluation(
            Syntax.identifierSyntaxAsString("pow"), 250, 2, new FunctionOperationEvaluation<Double>() {
                @Override
                public Maybe<Double> maybeValueResultOfApplyingFunctionOperationToListOfArguments(List<Double> givenArgumentList) {
                    Double num = givenArgumentList.get(0);
                    Double power = givenArgumentList.get(1);
                    return Maybe.asObject(Math.pow(num, power));
                }
            }
    );

    public static final Operation<CalculationValue> NTHROOT_OPERATION = Operations.functionOperationWithSyntaxAndBindingStrengthAndNumberOfArgumentsAndFunctionOperationEvaluation(
            Syntax.identifierSyntaxAsString("nthroot"), 250, 2, new FunctionOperationEvaluation<Double>() {
                @Override
                public Maybe<Double> maybeValueResultOfApplyingFunctionOperationToListOfArguments(List<Double> givenArgumentList) {
                    Double num = givenArgumentList.get(0);
                    Double root = givenArgumentList.get(1);
                    if (root != 0) {
                        return Maybe.asObject(Math.pow(num, 1.0 / root));
                    } else {
                        return Maybe.asNothing();
                    }
                }
            }
    );

    private static Operation<CalculationValue> infixOperationWithSyntaxAndBindingStrengthAndInfixOperationEvaluation(Syntax givenSyntax, int bindingStrength, final Operation.InfixOperationEvaluation<Double> infixOperationEvaluation) {
        return Operation.infixOperationWithSyntaxAndBindingStrengthAndInfixOperationEvaluation(givenSyntax, bindingStrength, new Operation.InfixOperationEvaluation<CalculationValue>() {
            @Override
            public Maybe<CalculationValue> maybeValueResultOfApplyingInfixOperationToLeftAndRightValue(CalculationValue givenLeftValue, CalculationValue givenRightValue) {
                Maybe<Double> givenLeftValueAsDouble = givenLeftValue.maybeValueAsDouble();
                Maybe<Double> givenRightValueAsDouble = givenRightValue.maybeValueAsDouble();
                if (givenLeftValueAsDouble.isNotNothing() && givenRightValueAsDouble.isNotNothing()) {
                    return _maybeDoubleToMaybeCalculationValue(infixOperationEvaluation.maybeValueResultOfApplyingInfixOperationToLeftAndRightValue(givenLeftValueAsDouble.object(), givenRightValueAsDouble.object()));
                } else {
                    return Maybe.asNothing();
                }
            }
        });
    }

    private static Operation<CalculationValue> prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax givenSyntax, int bindingStrength, final Operation.PrefixOperationEvaluation<Double> prefixOperationEvaluation) {
        return Operation.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(givenSyntax, bindingStrength, new Operation.PrefixOperationEvaluation<CalculationValue>() {
            @Override
            public Maybe<CalculationValue> maybeValueResultOfApplyingPrefixOperationToValue(CalculationValue givenValue) {
                Maybe<Double> givenValueAsDouble = givenValue.maybeValueAsDouble();
                if (givenValueAsDouble.isNotNothing()) {
                    return _maybeDoubleToMaybeCalculationValue(prefixOperationEvaluation.maybeValueResultOfApplyingPrefixOperationToValue(givenValueAsDouble.object()));
                } else {
                    return Maybe.asNothing();
                }
            }
        });
    }

    private static Operation<CalculationValue> constantOperationWithSyntaxConstantOperationEvaluation(Syntax givenSyntax, Operation.ConstantOperationEvaluation<Double> constantOperationEvaluation) {
        return Operation.constantOperationWithSyntaxConstantOperationEvaluation(givenSyntax, new Operation.ConstantOperationEvaluation<CalculationValue>() {
            @Override
            public Maybe<CalculationValue> maybeValueResultOfEvaluatingConstantToValue() {
                return _maybeDoubleToMaybeCalculationValue(constantOperationEvaluation.maybeValueResultOfEvaluatingConstantToValue());
            }
        });
    }

    private interface FunctionOperationEvaluation<VALUE> {
        public Maybe<VALUE> maybeValueResultOfApplyingFunctionOperationToListOfArguments(List<VALUE> givenArgumentList);
    }

    private static Operation<CalculationValue> functionOperationWithSyntaxAndBindingStrengthAndNumberOfArgumentsAndFunctionOperationEvaluation(Syntax givenSyntax, int bindingStrength, final int numberOfArguments, final FunctionOperationEvaluation<Double> functionOperationEvaluation) {
        return Operation.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(givenSyntax, bindingStrength, new Operation.PrefixOperationEvaluation<CalculationValue>() {
            @Override
            public Maybe<CalculationValue> maybeValueResultOfApplyingPrefixOperationToValue(CalculationValue givenValue) {
                Maybe<List<Double>> maybeListOfArguments = givenValue.maybeValueAsListOfDoublesOfLengthN(numberOfArguments);
                return maybeListOfArguments.applyGivenOperationOntoThisObjectMondically(new MonadicOperation<Monad<CalculationValue>, List<Double>, CalculationValue>() {
                    @Override
                    public Maybe<CalculationValue> performMonadicOperation(List<Double> listOfArguments) {
                        return _maybeDoubleToMaybeCalculationValue(functionOperationEvaluation.maybeValueResultOfApplyingFunctionOperationToListOfArguments(listOfArguments));
                    }
                });
            }
        });
    }

    private static Maybe<CalculationValue> _maybeDoubleToMaybeCalculationValue(Maybe<Double> maybeDouble) {
        return maybeDouble.applyGivenOperationOntoThisObjectMondically(new MonadicOperation<Monad<CalculationValue>, Double, CalculationValue>() {
            @Override
            public Maybe<CalculationValue> performMonadicOperation(Double aDouble) {
                return Maybe.asObject(CalculationValue.calculationValueAsDouble(aDouble));
            }
        });
    }


}
