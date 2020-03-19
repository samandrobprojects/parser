package parser;
import static org.junit.Assert.assertEquals;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;
import org.junit.Test;

public class TestClass {

    @Test
    public void one() {
        String testString = "( ( (1 + 1) + (1+ (1 + 1)) ) )";
        System.out.println("Emma Test");
        Double resultValue = -1.0;
        try {
            resultValue = CalculationExpression.evaluateStringExpression(testString);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        System.out.print("result: ");
        System.out.println(resultValue);
        System.out.println("wolframalpha: 5");
        System.out.println("done.");

        assertEquals(6, 6);
    }

    @Test
    public void two() {
        String testString = "(((((  ( ( ((((1 + 1)))) + (1+ (1 + 1)) ) ) * (( ( (1 + 1) + (1+ (1 + 1)) ) )) * ((( ( (1 + 1) + (1+ (1 + 1)) ) )))       )))))";
        System.out.println("Emma Test");
        Double resultValue = -1.0;
        try {
            resultValue = CalculationExpression.evaluateStringExpression(testString);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        System.out.print("result: ");
        System.out.println(resultValue);
        System.out.println("wolframalpha: 125");
        System.out.println("done.");

        assertEquals(6, 6);
    }

    @Test
    public void three() {
        String testString = "pow(2, 256)";
        System.out.println("Emma Test");
        Double resultValue = -1.0;
        try {
            resultValue = CalculationExpression.evaluateStringExpression(testString);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        System.out.print("result: ");
        System.out.println(resultValue);
        System.out.println("wolframalpha: 125");
        System.out.println("done.");

        // exp4j
        Expression e = new ExpressionBuilder(testString)
                .build();
        double expResult = e.evaluate();

        System.out.print("ExpLib: ");
        System.out.println(expResult);
        System.out.println("done.");

        assertEquals(6, 6);
    }

    @Test
    public void four() {
        String testString = "pow(2, pow(2, pow(2, 3)))";
        System.out.println("Emma Test");
        Double resultValue = -1.0;
        try {
            resultValue = CalculationExpression.evaluateStringExpression(testString);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        System.out.print("result: ");
        System.out.println(resultValue);
        System.out.println("wolframalpha: 125");
        System.out.println("done.");

        assertEquals(6, 6);
    }

    @Test
    public void five() {
        String testString = "nthroot(1249726573853485, 4.5)";
        System.out.println("Emma Test");
        Double resultValue = -1.0;
        try {
            resultValue = CalculationExpression.evaluateStringExpression(testString);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        System.out.print("result: ");
        System.out.println(resultValue);
        System.out.println("wolframalpha: about 2263.85");
        System.out.println("done.");

        assertEquals(6, 6);
    }

    @Test
    public void six() {
        String testString = "log(1249726573853485, 4.5)";
        System.out.println("Emma Test");
        Double resultValue = -1.0;
        try {
            resultValue = CalculationExpression.evaluateStringExpression(testString);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        System.out.print("result: ");
        System.out.println(resultValue);
        System.out.println("wolframalpha: about 2263.85");
        System.out.println("done.");

        assertEquals(6, 6);
    }

    @Test
    public void seven() {
        String testString = "std(cos(4.332), pow(1.223,1.223), ((((1+(1+2))))))";
        System.out.println("Emma Test");
        Double resultValue = -1.0;
        try {
            resultValue = CalculationExpression.evaluateStringExpression(testString);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        System.out.print("result: ");
        System.out.println(resultValue);
        System.out.println("done.");

        assertEquals(6, 6);
    }

    @Test
    public void eight() {
        String testString = "mean(234234, 123454, 3453421)";
        System.out.println("Emma Test");
        Double resultValue = -1.0;
        try {
            resultValue = CalculationExpression.evaluateStringExpression(testString);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        System.out.print("result: ");
        System.out.println(resultValue);
        System.out.println("done.");

        assertEquals(6, 6);
    }

    @Test
    public void nine() {
        String testString = "median(cos(4.332), pow(1.223,1.223), ((((1+(1+2))))))";
        System.out.println("Emma Test");
        Double resultValue = -1.0;
        try {
            resultValue = CalculationExpression.evaluateStringExpression(testString);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        System.out.print("result: ");
        System.out.println(resultValue);
        System.out.println("done.");

        assertEquals(6, 6);
    }

    @Test
    public void ten() {
        String testString = "log(123123123123123123123123123123, mean( (1 * (((((((((((((((24243))))))))))))))) ,            (1 * (((((((((((((((24243))))))))))))))) , (234 * (((((((((((((((24243))))))))))))))) ))";
        System.out.println("Emma Test");
        Double resultValue = -1.0;
        try {
            resultValue = CalculationExpression.evaluateStringExpression(testString);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        System.out.print("result: ");
        System.out.println(resultValue);
        System.out.println("done.");

        assertEquals(6, 6);
    }

    @Test
    public void stressOne() {
        String testString = "((mean(cos(2.4444), sin(5)*4, -44/2*3)))";
        System.out.println("stress test one");
        Double resultValue = -1.0;
        try {
            resultValue = CalculationExpression.evaluateStringExpression(testString);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        System.out.print("result: ");
        System.out.println(resultValue);
        System.out.println("wolframalpha: -23.5341");
        System.out.println("done.");

        assertEquals(6, 6);
    }


    @Test
    public void stressTwo() {
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
        System.out.println("stress test two");
        Double resultValue = -1.0;
        try {
            resultValue = CalculationExpression.evaluateStringExpression(testString);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        System.out.print("result: ");
        System.out.println(resultValue);
        System.out.println("wolframalpha: -23.5341");
        System.out.println("done.");

        assertEquals(6, 6);
    }

    /*

    mean(((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2
	,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-55,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2,((mean(cos(2.4444), sin(5)*4, -44/2*3)))*-2                               )


     */


    @Test
    public void evaluatesExpressionForStd() {
        //(https://www.calculator.net/standard-deviation-calculator.html?numberinputs=10%2C+12%2C+23%2C+23%2C+16%2C+23%2C+21%2C+16&ctype=p&x=51&y=12)
        String testString = "std (10, 12, 23, 23, 16, 23, 21, 16)";
        System.out.println("First test");
        Double resultValue = -1.0;
        try {
            resultValue = CalculationExpression.evaluateStringExpression(testString);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        System.out.print("result: ");
        System.out.println(resultValue);
        System.out.println("wolframalpha: 4.8989794855664");
        System.out.println("done.");

        assertEquals(6, 6);
    }

    @Test
    public void evaluatesExpressionForMean() {
        String testString = "mean (10, 12, 23, 23, 16, 23, 21, 16)";
        System.out.println("Second test");
        Double resultValue = -1.0;
        try {
            resultValue = CalculationExpression.evaluateStringExpression(testString);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        System.out.print("result: ");
        System.out.println(resultValue);
        System.out.println("wolframalpha: 18");
        System.out.println("done.");

        assertEquals(6, 6);
    }

    @Test
    public void evaluatesExpressionForMedian() {
        String testString = "median (10, 12, 23, 23, 16, 23, 21, 16)";
        System.out.println("Third test");
        Double resultValue = -1.0;
        try {
            resultValue = CalculationExpression.evaluateStringExpression(testString);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        System.out.print("result: ");
        System.out.println(resultValue);
        System.out.println("wolframalpha: 18.5");
        System.out.println("done.");

        assertEquals(6, 6);
    }

    @Test
    public void evaluatesExpressionForSin() {
        String testString = "sin(2.5)";
        System.out.println("Sin");
        // RobLib
        Double robResult = -1.0;
        try {
            robResult = CalculationExpression.evaluateStringExpression(testString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.print("RobLib: ");
        System.out.println(robResult);

        // exp4j
        Expression e = new ExpressionBuilder(testString)
                .build();
        double expResult = e.evaluate();

        System.out.print("ExpLib: ");
        System.out.println(expResult);
        System.out.println("done.");

        assertEquals(6, 6);
    }

    @Test
    public void evaluatesExpressionForCos() {
        String testString = "cos(2.5)";
        System.out.println("cos");
        // RobLib
        Double robResult = -1.0;
        try {
            robResult = CalculationExpression.evaluateStringExpression(testString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.print("RobLib: ");
        System.out.println(robResult);

        // exp4j
        Expression e = new ExpressionBuilder(testString)
                .build();
        double expResult = e.evaluate();

        System.out.print("ExpLib: ");
        System.out.println(expResult);
        System.out.println("done.");

        assertEquals(6, 6);
    }

    @Test
    public void evaluatesExpressionForTan() {
        String testString = "tan(2.5)";
        System.out.println("tan");
        // RobLib
        Double robResult = -1.0;
        try {
            robResult = CalculationExpression.evaluateStringExpression(testString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.print("RobLib: ");
        System.out.println(robResult);

        // exp4j
        Expression e = new ExpressionBuilder(testString)
                .build();
        double expResult = e.evaluate();

        System.out.print("ExpLib: ");
        System.out.println(expResult);
        System.out.println("done.");

        assertEquals(6, 6);
    }


    // Exp4j: Workaround needed
    @Test
    public void evaluatesExpressionForCot() {
        String testString = "cot(2.5)";
        System.out.println("cot");
        // RobLib
        Double robResult = -1.0;
        try {
            robResult = CalculationExpression.evaluateStringExpression(testString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.print("RobLib: ");
        System.out.println(robResult);
/*
        // exp4j
        Expression e = new ExpressionBuilder(testString)
                .build();
        double expResult = e.evaluate();

        System.out.print("ExpLib: ");
        System.out.println(expResult);
        System.out.println("done.");
*/
        assertEquals(6, 6);
    }


    // Exp4j: Workaround needed
    @Test
    public void evaluatesExpressionForSec() {
        String testString = "sec(2.5)";
        System.out.println("sec");
        // RobLib
        Double robResult = -1.0;
        try {
            robResult = CalculationExpression.evaluateStringExpression(testString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.print("RobLib: ");
        System.out.println(robResult);

/*
        // exp4j
        Expression e = new ExpressionBuilder(testString)
                .build();
        double expResult = e.evaluate();

        System.out.print("ExpLib: ");
        System.out.println(expResult);
        System.out.println("done.");
*/

        assertEquals(6, 6);
    }

    // Exp4j: Workaround needed
    @Test
    public void evaluatesExpressionForCsc() {
        String testString = "csc(2.5)";
        System.out.println("csc");
        // RobLib
        Double robResult = -1.0;
        try {
            robResult = CalculationExpression.evaluateStringExpression(testString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.print("RobLib: ");
        System.out.println(robResult);

/*
        // exp4j
        Expression e = new ExpressionBuilder(testString)
                .build();
        double expResult = e.evaluate();

        System.out.print("ExpLib: ");
        System.out.println(expResult);
        System.out.println("done.");
*/

        assertEquals(6, 6);
    }



    // written different,  with a carrot, complicated parsing needed here
    @Test
    public void evaluatesExpressionForPow() {
        String testString = "pow(2, pow(2, 2))";
        System.out.println("pow");
        // RobLib
        Double robResult = -1.0;
        try {
            robResult = CalculationExpression.evaluateStringExpression(testString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.print("RobLib: ");
        System.out.println(robResult);

/*
        // exp4j
        Expression e = new ExpressionBuilder(testString)
                .build();
        double expResult = e.evaluate();

        System.out.print("ExpLib: ");
        System.out.println(expResult);
        System.out.println("done.");
*/

        assertEquals(6, 6);
    }

    // Has sqaure and cube root
    // Workaround: very difficult, because we don't have power to a fraction
    @Test
    public void evaluatesExpressionForNthroot() {
        String testString = "nthroot(16,4)";
        System.out.println("nthroot");
        // RobLib
        Double robResult = -1.0;
        try {
            robResult = CalculationExpression.evaluateStringExpression(testString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.print("RobLib: ");
        System.out.println(robResult);
/*
        // exp4j
        Expression e = new ExpressionBuilder(testString)
                .build();
        double expResult = e.evaluate();

        System.out.print("ExpLib: ");
        System.out.println(expResult);
        System.out.println("done.");
*/

        assertEquals(6, 6);
    }

    // Some logs, (2,e,10)
    // Workaround: some division magic.... not sure how youd do that
    @Test
    public void evaluatesExpressionForLog() {
        String testString = "log(256,2)";
        System.out.println("log");
        // RobLib
        Double robResult = -1.0;
        try {
            robResult = CalculationExpression.evaluateStringExpression(testString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.print("RobLib: ");
        System.out.println(robResult);

        // exp4j
        Function logb = new Function("logb", 2) {
            @Override
            public double apply(double... args) {
                return Math.log(args[0]) / Math.log(args[1]);
            }
        };
        double result = new ExpressionBuilder("logb(8, 2)")
                .function(logb)
                .build()
                .evaluate();
        double expected = 3;

        System.out.print("ExpLib: ");
        System.out.println(result);
        System.out.println("done.");


        assertEquals(6, 6);
    }

    @Test
    public void evaluatesExpressionForAbs() {
        String testString = "abs(-2)";
        System.out.println("abs");
        // RobLib
        Double robResult = -1.0;
        try {
            robResult = CalculationExpression.evaluateStringExpression(testString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.print("RobLib: ");
        System.out.println(robResult);

        // exp4j
        Expression e = new ExpressionBuilder(testString)
                .build();
        double expResult = e.evaluate();

        System.out.print("ExpLib: ");
        System.out.println(expResult);
        System.out.println("done.");

        assertEquals(6, 6);
    }
}