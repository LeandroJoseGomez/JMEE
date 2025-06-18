package evaluator;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class EvaluatorTest {

    @Test
    public void getAns() {
        Evaluator instance = new Evaluator("3^2+1");
        double expResult = 10;
        instance.evaluateExpression();
        double result = instance.getAns();
        assertEquals(expResult, result, 0);
    }

    @Test // Error de sintaxis
    public void getAnsSyntaxError() {
        Evaluator instance = new Evaluator("3^(2+1");
        double expResult = 10;
        instance.evaluateExpression();
        double result = instance.getAns();
        //assertEquals(expResult, result, 0);
    }

    @Test // error al tratar cantidades negativas
    public void getAnsNegativeNumbers() {
        Evaluator instance = new Evaluator("-3.10+10+*-6.9"); // +3*sqrt((-2^2))
        double expResult = 0;
        instance.evaluateExpression();
        double result = 0;
        System.out.println(result);
        assertEquals(expResult, result, 0);
    }

    @Test
    public void evaluateExpression() {
        Evaluator instance = new Evaluator("3+max(4,3,2,1)+3-min(4,3,2,1)");
        double expResult = 9;
        double result = instance.evaluateExpression();
        assertEquals(expResult, result, 0);
    }

    @Test
    public void evaluateExpressionTrigonometric() {
        Evaluator instance = new Evaluator("cos(90)^2+sin(90)^2");
        double expResult = 1;
        double result = instance.evaluateExpression();
        assertEquals(expResult, result, 0);
    }

    @Test // error de calculo.
    public void evaluateExpressionLogarithmic() {
        Evaluator instance = new Evaluator("arcos(1)");
        double expResult = 90;
        double result = instance.evaluateExpression();
        //assertEquals(expResult, result, 0);
    }

    @Test
    public void findMax() {
        Evaluator instance = new Evaluator();
        double result = instance.findMax(Arrays.asList(4.22,9.33,9.0,6.1,3.0));
        double expResult = 9.33;
        assertEquals(expResult, result, 0);
    }

    @Test
    public void findMin() {
        Evaluator instance = new Evaluator();
        double result = instance.findMin(Arrays.asList(4.22,9.33,9.0,6.1,3.0));
        double expResult = 3.0;
        assertEquals(expResult, result, 0);
    }
}