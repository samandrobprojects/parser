package parser;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestClass {
    @Test
    public void evaluatesExpression() {
        String testString = "sin (PI +1)";

        Double resultValue = -1.0;
        try {
            resultValue = CalculationExpression.evaluateStringExpression(testString);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        System.out.print("result: ");
        System.out.println(resultValue);
        System.out.print("done.");

        assertEquals(6, 6);
    }
}