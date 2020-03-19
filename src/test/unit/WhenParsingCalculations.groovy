import parser.CalculationExpression
import parser.functional.Maybe
import spock.lang.Specification
import spock.lang.Unroll

class WhenParsingCalculations extends Specification {

    def "Example"() {
        given: "an expression string"
        String testString = "sin(2.5)"

        when: "calculating that expression"
        Double result = CalculationExpression.evaluateStringExpression(testString)

        then: "the expected result is the same as running it directly through Java Math library"
        result == (Math.sin(2.5))
    }

    // SIN
    @Unroll
    def "Test simple sin calculations work"() {
        expect: "Each of the inputs has the correct output"
        expectedResult == CalculationExpression.evaluateStringExpression(expressionString)

        where: "we test a batch of different sin expression strings"
        expressionString     || expectedResult
        "sin(1)"             || Math.sin(1)
        "sin(2.5)"           || Math.sin(2.5)
        "sin(-12334)"        || Math.sin(-12334)
        "sin(2234234234)"    || Math.sin(2234234234)
        "sin(0)"             || Math.sin(0)
    }

    // COS
    @Unroll
    def "Test simple cos calculations work"() {
        expect: "Each of the inputs has the correct output"
        expectedResult == CalculationExpression.evaluateStringExpression(expressionString)

        where: "we test a batch of different cos expression strings"
        expressionString     || expectedResult
        "cos(1)"             || Math.cos(1)
        "cos(2.5)"           || Math.cos(2.5)
        "cos(-12334)"        || Math.cos(-12334)
        "cos(2234234234)"    || Math.cos(2234234234)
        "cos(0)"             || Math.cos(0)
    }

    // TAN
    @Unroll
    def "Test simple tan calculations work"() {
        expect: "Each of the inputs has the correct output"
        expectedResult == CalculationExpression.evaluateStringExpression(expressionString)

        where: "we test a batch of different tan expression strings"
        expressionString     || expectedResult
        "tan(1)"             || Math.tan(1)
        "tan(2.5)"           || Math.tan(2.5)
        "tan(-12334)"        || Math.tan(-12334)
        "tan(2234234234)"    || Math.tan(2234234234)
        "tan(0)"             || Math.tan(0)
    }

    // COTAN
    @Unroll
    def "Test simple cotan calculations work"() {
        expect: "Each of the inputs has the correct output"
        expectedResult == CalculationExpression.evaluateStringExpression(expressionString)

        where: "we test a batch of different cotan expression strings"
        expressionString     || expectedResult
        "cot(1)"             || 1.0 / Math.tan(1)
        "cot(2.5)"           || 1.0 / Math.tan(2.5)
        "cot(-12334)"        || 1.0 / Math.tan(-12334)
        "cot(2234234234)"    || 1.0 / Math.tan(2234234234)
        //"cot(0)"             || 1.0 / Math.tan(0) ZERO CANNOT WORK IS IS UNDEFINED
    }

    // COSECANT
    @Unroll
    def "Test simple cosecant calculations work"() {
        expect: "Each of the inputs has the correct output"
        expectedResult == CalculationExpression.evaluateStringExpression(expressionString)

        where: "we test a batch of different cosecant expression strings"
        expressionString     || expectedResult
        "csc(1)"             || 1.0 / Math.cos(1)
        "csc(2.5)"           || 1.0 / Math.cos(2.5)
        "csc(-12334)"        || 1.0 / Math.cos(-12334)
        "csc(2234234234)"    || 1.0 / Math.cos(2234234234)
        "csc(0)"             || 1.0 / Math.cos(0)
    }

    // SECANT
    @Unroll
    def "Test simple secant calculations work"() {
        expect: "Each of the inputs has the correct output"
        expectedResult == CalculationExpression.evaluateStringExpression(expressionString)

        where: "we test a batch of different secant expression strings"
        expressionString     || expectedResult
        "sec(1)"             || 1.0 / Math.sin(1)
        "sec(2.5)"           || 1.0 / Math.sin(2.5)
        "sec(-12334)"        || 1.0 / Math.sin(-12334)
        "sec(2234234234)"    || 1.0 / Math.sin(2234234234)
        //"sec(0)"             || 1.0 / Math.sin(0) THIS CANNOT WORK FOR SIN 0
    }

    // ABS
    @Unroll
    def "Test simple absoloute value calculations work"() {
        expect: "Each of the inputs has the correct output"
        expectedResult == CalculationExpression.evaluateStringExpression(expressionString)

        where: "we test a batch of different absoloute value expression strings"
        expressionString     || expectedResult
        "abs(1)"             || Math.abs(1)
        "abs(2.5)"           || Math.abs(2.5)
        "abs(-12334)"        || Math.abs(-12334)
        "abs(2234234234)"    || Math.abs(2234234234)
        "abs(0)"             || Math.abs(0)
    }

    // POWER
    @Unroll
    def "Test simple power calculation works"() {
        expect: "Each of the inputs has the correct output"
        expectedResult == CalculationExpression.evaluateStringExpression(expressionString)

        where: "we test a batch of different power expression strings"
        expressionString             || expectedResult
        "pow(22.52, 1.782)"          || Math.pow(22.52, 1.782)
        "pow(22.234234, 1.23423482)" || Math.pow(22.234234, 1.23423482)
        "pow(4, 7)"                  || Math.pow(4, 7)
        "pow(256, -4)"               || Math.pow(256, -4)
        "pow(-16, 4)"                || Math.pow(-16, 4)
        "pow(-16, -4)"               || Math.pow(-16, -4)
    }

    // LOG
    @Unroll
    def "Test simple log calculation works"() {
        expect: "Each of the inputs has the correct output"
        expectedResult == CalculationExpression.evaluateStringExpression(expressionString)

        where: "we test a batch of different log expression strings"
        expressionString             || expectedResult
        "log(1231423, 3)"            || Math.log(1231423) / Math.log(3)
        "log(22.234234, 1.23423482)" || Math.log(22.234234) / Math.log(1.23423482)
        "log(4, 7)"                  || Math.log(4) / Math.log(7)
        "log(1, 1)"                  || Math.log(1) / Math.log(1)
        "log(0, 0)"                  || Math.log(0) / Math.log(0)
        "log(-16, -4)"               || Math.log(-16) / Math.log(-4)
    }

    //NTHROOT
    @Unroll
    def "Test simple nthroot calculation works"() {
        expect: "Each of the inputs has the correct output"
        expectedResult == CalculationExpression.evaluateStringExpression(expressionString)

        where: "we test a batch of different nthroot expression strings"
        expressionString                   || expectedResult
        "nthroot(1231423, 3)"              || (Double) 107.1854297695904
        "nthroot(22.234234, 1.23423482)"   || (Double) 12.341910983088075
        "nthroot(4.0, 7.0)"                || (Double) 1.2190136542044754
        "nthroot(1.0, 1.0)"                || (Double) 1.0
        "nthroot(-16, -4)"                 || (Double) Math.pow(-16, 1.0 / -4.0)
        //"nthroot(0, 0)"                  || Math.pow(0, 1.0 / 0.0) CANNOT DIVIDE BY ZERO
    }

    // BRACKETS
    @Unroll
    def "Test combination of brackets works"() {
        expect: "Each of the inputs has the correct output"
        expectedResult == CalculationExpression.evaluateStringExpression(expressionString)

        where: "we test a batch of different bracketed expression strings"
        expressionString                   || expectedResult
        "((1+((2)+(3)+(((4))))))"          || 10.0
        "((1+((2*(2))+(3)+(((4))))))"      || 12.0
        "((1+((2)+(3)+(((4-(-4)4)))))))"   || 6.0
    }

    // STRESS
    def "When given a very large input string the calculation is robust"() {
        given: "a vary large expression string"
        String testString = "mean(((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                "" +
                "                                                       \n\t" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-55,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                ",((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2" +
                "                               " +
                ")";

        when: "calculating that expression"
        Double result = CalculationExpression.evaluateStringExpression(testString)

        then: "the expected result"
        result == (Double) 54.320021105233735
    }

    // STRESS
    def "When given another very large input string the calculation is robust"() {
        given: "a vary large expression string"
        String testString = "log(123123123123123123123123123123, mean( (1 * (((((((((((((((24243))))))))))))))) ,            (1 * (((((((((((((((24243))))))))))))))) , (234 * (((((((((((((((24243))))))))))))))) ))"

        when: "calculating that expression"
        Double result = CalculationExpression.evaluateStringExpression(testString)

        then: "the expected result"
        result == (Double) 4.631941540280599
    }

    // MEAN
    public Double getMeanOfGivenNumbers(List<Double> giveDoubleList) {
        Double result = 0;
        for(Double element : giveDoubleList) {
            result += element;
        }
        return result / (Double) giveDoubleList.size();
    }
    @Unroll
    def "Test combination of mean expressions works"() {
        expect: "Each of the inputs has the correct output"
        expectedResult == CalculationExpression.evaluateStringExpression(expressionString)

        where: "we test a batch of different mean expression strings"
        expressionString                     || expectedResult
        "mean(1, 2, 3, 4, 5)"                || getMeanOfGivenNumbers(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0))
        "mean(123, 12.22556, 434.0234)"      || getMeanOfGivenNumbers(Arrays.asList(123.0, 12.22556, 434.0234))
        "mean(-2342.234, -0.0003, 50)"       || getMeanOfGivenNumbers(Arrays.asList(-2342.234, -0.0003, 50.0))
    }

    // MEDIAN
    public Double getMedianOfGivenNumbers(List<Double> giveDoubleList) {
        boolean argumentListIsEvenSize = (giveDoubleList.size() % 2 == 0);
        List<Double> sortedArgumentList = new ArrayList<>(giveDoubleList);
        Collections.sort(sortedArgumentList);
        if (argumentListIsEvenSize) {
            int rightMiddleIndex = (int)(sortedArgumentList.size() / 2.0);
            int leftMiddleIndex = rightMiddleIndex - 1;
            return (sortedArgumentList.get(leftMiddleIndex) + sortedArgumentList.get(rightMiddleIndex)) / 2.0;
        } else {
            int middleIndex = (int)Math.floor(sortedArgumentList.size() / 2.0);
            return sortedArgumentList.get(middleIndex);
        }
    }
    @Unroll
    def "Test combination of median expressions works"() {
        expect: "Each of the inputs has the correct output"
        expectedResult == CalculationExpression.evaluateStringExpression(expressionString)

        where: "we test a batch of different median expression strings"
        expressionString                       || expectedResult
        "median(1, 2, 3, 4, 5)"                || getMedianOfGivenNumbers(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0))
        "median(123, 12.22556, 434.0234)"      || getMedianOfGivenNumbers(Arrays.asList(123.0, 12.22556, 434.0234))
        "median(2342.234, 0.0003, 50)"         || getMedianOfGivenNumbers(Arrays.asList(2342.234, 0.0003, 50.0))
    }
}
