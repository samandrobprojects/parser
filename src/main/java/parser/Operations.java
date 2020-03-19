package parser;

import parser.functional.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Operations {

    private static final int BINDING_STRENGTH_LOW = 50;
    private static final int BINDING_STRENGTH_MEDIUM = 100;
    private static final int BINDING_STRENGTH_HIGH = 200;
    private static final int BINDING_STRENGTH_HIGHEST = 250;

    public static class Constants {

        public static final Operation<CalculationValue> PI_CONSTANT = Operations.constantOperationWithSyntaxConstantOperationEvaluation(Syntax.identifierSyntaxAsString("PI"), new Operation.ConstantOperationEvaluation<Double>() {
            @Override
            public Maybe<Double> maybeValueResultOfEvaluatingConstantToValue() {
                return Maybe.asObject(Math.PI);
            }
        });

    }

    public static final Operation<CalculationValue> ADDITION_OPERATION = Operations.infixOperationWithSyntaxAndBindingStrengthAndInfixOperationEvaluation(Syntax.atomicSyntaxAsCharacter('+'), BINDING_STRENGTH_LOW, new Operation.InfixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingInfixOperationToLeftAndRightValue(Double givenLeftValue, Double givenRightValue) {
            return Maybe.asObject(givenLeftValue + givenRightValue);
        }
    });

    public static final Operation<CalculationValue> SUBTRACTION_OPERATION = Operations.infixOperationWithSyntaxAndBindingStrengthAndInfixOperationEvaluation(Syntax.atomicSyntaxAsCharacter('-'), BINDING_STRENGTH_LOW, new Operation.InfixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingInfixOperationToLeftAndRightValue(Double givenLeftValue, Double givenRightValue) {
            System.out.println("HELLO");
            System.out.println(givenLeftValue);
            System.out.println(givenRightValue);
            System.out.println(givenLeftValue - givenRightValue);
            return Maybe.asObject(givenLeftValue - givenRightValue);
        }
    });

    public static final Operation<CalculationValue> MULTIPLICATION_OPERATION = Operations.infixOperationWithSyntaxAndBindingStrengthAndInfixOperationEvaluation(Syntax.atomicSyntaxAsCharacter('*'), BINDING_STRENGTH_MEDIUM, new Operation.InfixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingInfixOperationToLeftAndRightValue(Double givenLeftValue, Double givenRightValue) {
            return Maybe.asObject(givenLeftValue * givenRightValue);
        }
    });

    public static final Operation<CalculationValue> DIVISION_OPERATION = Operations.infixOperationWithSyntaxAndBindingStrengthAndInfixOperationEvaluation(Syntax.atomicSyntaxAsCharacter('/'), BINDING_STRENGTH_MEDIUM, new Operation.InfixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingInfixOperationToLeftAndRightValue(Double givenLeftValue, Double givenRightValue) {
            if (givenRightValue == 0) {
                return Maybe.asNothing();
            } else {
                return Maybe.asObject(givenLeftValue / givenRightValue);
            }
        }
    });

    public static final Operation<CalculationValue> MODULO_OPERATION = Operations.infixOperationWithSyntaxAndBindingStrengthAndInfixOperationEvaluation(Syntax.atomicSyntaxAsCharacter('%'), BINDING_STRENGTH_MEDIUM, new Operation.InfixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingInfixOperationToLeftAndRightValue(Double givenLeftValue, Double givenRightValue) {
            if (givenRightValue == 0) {
                return Maybe.asNothing();
            } else {
                return Maybe.asObject(givenLeftValue % givenRightValue);
            }
        }
    });

    public static final Operation<CalculationValue> SIN_OPERATION_IN_RADIANS = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("sin"), BINDING_STRENGTH_HIGH, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperation) {
            return Maybe.asObject(Math.sin(valueToApplyOperation));
        }
    });

    public static final Operation<CalculationValue> SIN_OPERATION_IN_DEGREES = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("sin"), BINDING_STRENGTH_HIGH, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperationInDegrees) {
            Double valueToApplyOperationInRadians = Math.toRadians(valueToApplyOperationInDegrees);
            return Maybe.asObject(Math.sin(valueToApplyOperationInRadians));
        }
    });

    public static final Operation<CalculationValue> COS_OPERATION_IN_RADIANS = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("cos"), BINDING_STRENGTH_HIGH, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperation) {
            return Maybe.asObject(Math.cos(valueToApplyOperation));
        }
    });

    public static final Operation<CalculationValue> COS_OPERATION_IN_DEGREES = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("cos"), BINDING_STRENGTH_HIGH, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperationInDegrees) {
            Double valueToApplyOperationInRadians = Math.toRadians(valueToApplyOperationInDegrees);
            return Maybe.asObject(Math.cos(valueToApplyOperationInRadians));
        }
    });

    public static final Operation<CalculationValue> TAN_OPERATION_IN_RADIANS = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("tan"), BINDING_STRENGTH_HIGH, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperation) {
            return Maybe.asObject(Math.tan(valueToApplyOperation));
        }
    });

    public static final Operation<CalculationValue> TAN_OPERATION_IN_DEGREES = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("tan"), BINDING_STRENGTH_HIGH, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperationInDegrees) {
            Double valueToApplyOperationInRadians = Math.toRadians(valueToApplyOperationInDegrees);
            return Maybe.asObject(Math.tan(valueToApplyOperationInRadians));
        }
    });

    public static final Operation<CalculationValue> COT_OPERATION_IN_RADIANS = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("cot"), BINDING_STRENGTH_HIGH, new Operation.PrefixOperationEvaluation<Double>() {
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

    public static final Operation<CalculationValue> COT_OPERATION_IN_DEGREES = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("cot"), BINDING_STRENGTH_HIGH, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperationInDegrees) {
            Double valueToApplyOperationInRadians = Math.toRadians(valueToApplyOperationInDegrees);
            Double tangentOfValue = Math.tan(valueToApplyOperationInRadians);
            if (tangentOfValue != 0.0) {
                return Maybe.asObject(1.0 / tangentOfValue);
            } else {
                return Maybe.asNothing();
            }
        }
    });

    public static final Operation<CalculationValue> SEC_OPERATION_IN_RADIANS = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("sec"), BINDING_STRENGTH_HIGH, new Operation.PrefixOperationEvaluation<Double>() {
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

    public static final Operation<CalculationValue> SEC_OPERATION_IN_DEGREES = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("sec"), BINDING_STRENGTH_HIGH, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperationInDegrees) {
            Double valueToApplyOperationInRadians = Math.toRadians(valueToApplyOperationInDegrees);
            Double sinOfValue = Math.sin(valueToApplyOperationInRadians);
            if (sinOfValue != 0.0) {
                return Maybe.asObject(1.0 / sinOfValue);
            } else {
                return Maybe.asNothing();
            }
        }
    });

    public static final Operation<CalculationValue> CSC_OPERATION_IN_RADIANS = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("csc"), BINDING_STRENGTH_HIGH, new Operation.PrefixOperationEvaluation<Double>() {
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

    public static final Operation<CalculationValue> CSC_OPERATION_IN_DEGREES = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("csc"), BINDING_STRENGTH_HIGH, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperationInDegrees) {
            Double valueToApplyOperationInRadians = Math.toRadians(valueToApplyOperationInDegrees);
            Double cosOfValue = Math.cos(valueToApplyOperationInRadians);
            if (cosOfValue != 0.0) {
                return Maybe.asObject(1.0 / cosOfValue);
            } else {
                return Maybe.asNothing();
            }
        }
    });

    public static final Operation<CalculationValue> ARCTAN_OPERATION_IN_RADIANS = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("arctan"), BINDING_STRENGTH_HIGH, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperation) {
            return Maybe.asObject(Math.atan(valueToApplyOperation));
        }
    });

    public static final Operation<CalculationValue> ARCTAN_OPERATION_IN_DEGREES = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("arctan"), BINDING_STRENGTH_HIGH, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperationInDegrees) {
            Double valueToApplyOperationInRadians = Math.toRadians(valueToApplyOperationInDegrees);
            return Maybe.asObject(Math.atan(valueToApplyOperationInRadians));
        }
    });

    public static final Operation<CalculationValue> ARCSIN_OPERATION_IN_RADIANS = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("arcsin"), BINDING_STRENGTH_HIGH, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperation) {
            return Maybe.asObject(Math.asin(valueToApplyOperation));
        }
    });

    public static final Operation<CalculationValue> ARCSIN_OPERATION_IN_DEGREES = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("arcsin"), BINDING_STRENGTH_HIGH, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperationInDegrees) {
            Double valueToApplyOperationInRadians = Math.toRadians(valueToApplyOperationInDegrees);
            return Maybe.asObject(Math.asin(valueToApplyOperationInRadians));
        }
    });

    public static final Operation<CalculationValue> ARCCOS_OPERATION_IN_RADIANS = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("arccos"), BINDING_STRENGTH_HIGH, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperation) {
            return Maybe.asObject(Math.acos(valueToApplyOperation));
        }
    });

    public static final Operation<CalculationValue> ARCCOS_OPERATION_IN_DEGREES = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("arccos"), BINDING_STRENGTH_HIGH, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperationInDegrees) {
            Double valueToApplyOperationInRadians = Math.toRadians(valueToApplyOperationInDegrees);
            return Maybe.asObject(Math.acos(valueToApplyOperationInRadians));
        }
    });

    public static final Operation<CalculationValue> ABS_OPERATION = Operations.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax.identifierSyntaxAsString("abs"), BINDING_STRENGTH_HIGH, new Operation.PrefixOperationEvaluation<Double>() {
        @Override
        public Maybe<Double> maybeValueResultOfApplyingPrefixOperationToValue(Double valueToApplyOperation) {
            return Maybe.asObject(Math.abs(valueToApplyOperation));
        }
    });

    public static final Operation<CalculationValue> LOG_OPERATION = Operations.functionOperationWithSyntaxAndBindingStrengthAndNumberOfArgumentsAndFunctionOperationEvaluation(
            Syntax.identifierSyntaxAsString("log"), BINDING_STRENGTH_HIGHEST, 2, new FunctionOperationEvaluation<Double>() {
                @Override
                public Maybe<Double> maybeValueResultOfApplyingFunctionOperationToListOfArguments(List<Double> givenArgumentList) {
                    Double x = givenArgumentList.get(0);
                    Double base = givenArgumentList.get(1);
                    return Maybe.asObject(Math.log(x) / Math.log(base));
                }
            }
    );

    public static final Operation<CalculationValue> POW_OPERATION = Operations.functionOperationWithSyntaxAndBindingStrengthAndNumberOfArgumentsAndFunctionOperationEvaluation(
            Syntax.identifierSyntaxAsString("pow"), BINDING_STRENGTH_HIGHEST, 2, new FunctionOperationEvaluation<Double>() {
                @Override
                public Maybe<Double> maybeValueResultOfApplyingFunctionOperationToListOfArguments(List<Double> givenArgumentList) {
                    Double num = givenArgumentList.get(0);
                    Double power = givenArgumentList.get(1);
                    return Maybe.asObject(Math.pow(num, power));
                }
            }
    );

    public static final Operation<CalculationValue> NTHROOT_OPERATION = Operations.functionOperationWithSyntaxAndBindingStrengthAndNumberOfArgumentsAndFunctionOperationEvaluation(
            Syntax.identifierSyntaxAsString("nthroot"), BINDING_STRENGTH_HIGHEST, 2, new FunctionOperationEvaluation<Double>() {
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

    public static final Operation<CalculationValue> MEAN_OPERATION = Operations.functionOperationOfVariableArgumentsWithSyntaxAndBindingStrengthAndFunctionOperationEvaluation(
            Syntax.identifierSyntaxAsString("mean"), BINDING_STRENGTH_HIGHEST, new FunctionOperationEvaluation<Double>() {
                @Override
                public Maybe<Double> maybeValueResultOfApplyingFunctionOperationToListOfArguments(List<Double> givenArgumentList) {
                    return Maybe.asObject(_meanOfListOfDoubles(givenArgumentList));
                }
            }
    );

    public static final Operation<CalculationValue> MEDIAN_OPERATION = Operations.functionOperationOfVariableArgumentsWithSyntaxAndBindingStrengthAndFunctionOperationEvaluation(
            Syntax.identifierSyntaxAsString("median"), BINDING_STRENGTH_HIGHEST, new FunctionOperationEvaluation<Double>() {
                @Override
                public Maybe<Double> maybeValueResultOfApplyingFunctionOperationToListOfArguments(List<Double> givenArgumentList) {
                    boolean argumentListIsEvenSize = (givenArgumentList.size() % 2 == 0);
                    List<Double> sortedArgumentList = new ArrayList<>(givenArgumentList);
                    Collections.sort(sortedArgumentList);
                    if (argumentListIsEvenSize) {
                        int rightMiddleIndex = (int)(sortedArgumentList.size() / 2.0);
                        int leftMiddleIndex = rightMiddleIndex - 1;
                        return Maybe.asObject((sortedArgumentList.get(leftMiddleIndex) + sortedArgumentList.get(rightMiddleIndex)) / 2.0);
                    } else {
                        int middleIndex = (int)Math.floor(sortedArgumentList.size() / 2.0);
                        return Maybe.asObject(sortedArgumentList.get(middleIndex));
                    }
                }
            }
    );

    public static final Operation<CalculationValue> STD_OPERATION = Operations.functionOperationOfVariableArgumentsWithSyntaxAndBindingStrengthAndFunctionOperationEvaluation(
            Syntax.identifierSyntaxAsString("std"), BINDING_STRENGTH_HIGHEST, new FunctionOperationEvaluation<Double>() {
                @Override
                public Maybe<Double> maybeValueResultOfApplyingFunctionOperationToListOfArguments(List<Double> givenArgumentList) {
                    final Double meanOfArgumentList = _meanOfListOfDoubles(givenArgumentList);
                    List<Double> squredMeanDifferencesOfArgumentList = _mapTransformationToDoublesInList(new DoubleTransformation() {
                        @Override
                        public Double applyTransformation(Double inputDouble) {
                            return (inputDouble - meanOfArgumentList)*(inputDouble - meanOfArgumentList);
                        }
                    }, givenArgumentList);
                    Double standardDeviation = Math.sqrt(_meanOfListOfDoubles(squredMeanDifferencesOfArgumentList));
                    return Maybe.asObject(standardDeviation);
                }
            }
    );

    /**
            Supporting private functions
     */

    private static Double _sumOfListOfDoubles(List<Double> givenListOfDoubles) {
        Double sumOfListOfDoubles = 0.0;
        for (Double doubleInList : givenListOfDoubles) {
            sumOfListOfDoubles += doubleInList;
        }
        return sumOfListOfDoubles;
    }

    private static Double _meanOfListOfDoubles(List<Double> givenListOfDoubles) {
        return _sumOfListOfDoubles(givenListOfDoubles) / (double)givenListOfDoubles.size();
    }

    private interface DoubleTransformation {
        public Double applyTransformation(Double inputDouble);
    }

    private static List<Double> _mapTransformationToDoublesInList(DoubleTransformation givenTransformaation, List<Double> givenListOfDoubles) {
        ArrayList<Double> transformedDoubles = new ArrayList<>();
        for (Double doubleInList : givenListOfDoubles) {
            transformedDoubles.add(givenTransformaation.applyTransformation(doubleInList));
        }
        return transformedDoubles;
    }

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

    private static Operation<CalculationValue> functionOperationOfVariableArgumentsWithSyntaxAndBindingStrengthAndFunctionOperationEvaluation(Syntax givenSyntax, int bindingStrength, final FunctionOperationEvaluation<Double> functionOperationEvaluation) {
        return Operation.prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(givenSyntax, bindingStrength, new Operation.PrefixOperationEvaluation<CalculationValue>() {
            @Override
            public Maybe<CalculationValue> maybeValueResultOfApplyingPrefixOperationToValue(CalculationValue givenValue) {
                Maybe<List<Double>> maybeListOfArguments = givenValue.maybeValueAsListOfDoublesOfLengthOneOrGreater();
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
