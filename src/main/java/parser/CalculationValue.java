package parser;

import parser.functional.*;

import java.util.ArrayList;
import java.util.List;

public class CalculationValue {
    private final ArrayList<Double> _listOfDoublesInValue;

    private CalculationValue(ArrayList<Double> listOfDoublesInValue) {
        _listOfDoublesInValue = listOfDoublesInValue;
    }

    public static CalculationValue calculationValueAsDouble(Double givenDouble) {
        ArrayList<Double> listOfDoublesInResultingValue = new ArrayList<>();
        listOfDoublesInResultingValue.add(givenDouble);
        return new CalculationValue(listOfDoublesInResultingValue);
    }

    public static CalculationValue calculationValueByConcatenatingValueToValue(CalculationValue calculationValue1, CalculationValue calculationValue2) {
        ArrayList<Double> listOfDoublesInResultingValue = new ArrayList<>(calculationValue1._listOfDoublesInValue);
        listOfDoublesInResultingValue.addAll(calculationValue2._listOfDoublesInValue);
        return new CalculationValue(listOfDoublesInResultingValue);
    }

    public Maybe<Double> maybeValueAsDouble() {
        if (_listOfDoublesInValue.size() == 1) {
            return Maybe.asObject(_listOfDoublesInValue.get(0));
        } else {
            return Maybe.asNothing();
        }
    }

    public Maybe<List<Double>> maybeValueAsListOfDoublesOfLengthN(int n) {
        if (_listOfDoublesInValue.size() == n) {
            return Maybe.asObject(_listOfDoublesInValue);
        } else {
            return Maybe.asNothing();
        }
    }

    public Maybe<List<Double>> maybeValueAsListOfDoublesOfLengthOneOrGreater() {
        if (_listOfDoublesInValue.size() >= 1) {
            return Maybe.asObject(_listOfDoublesInValue);
        } else {
            return Maybe.asNothing();
        }
    }
}
