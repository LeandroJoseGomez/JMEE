package evaluator;

import org.junit.Test;

import static org.junit.Assert.*;

public class EvaluatorTest {

    @Test
    public void evaluateExpression() {
        for (int i = 0; i<= 1000; i++){
            Evaluator instance = new Evaluator("-7/(-3+-3.33*4- -20/10)^2");
            instance.parseExpression();
            double expResult = -0.034135950813020816;

            double result = instance.evaluateExpression();
            System.out.println(result);
            assertEquals(expResult, result, 0);
        }

    }

    @Test
    public void evaluateExpressionTrigonometric() {
        Evaluator instance = new Evaluator("sIn(90) + CoS(90)");
        instance.parseExpression();
        double expResult = 1;

        double result = instance.evaluateExpression();
        assertEquals(expResult, result, 0);
    }

    @Test // error de calculo.
    public void evaluateExpressionWhitVariables() {
        Evaluator instance = new Evaluator("-7/(-3+-x*4- -20/10)^y");
        instance.setParameter("x", 3.33); // siempre en minusculas.
        instance.setParameter("y", 2);
        instance.parseExpression();
        double expResult = -0.034135950813020816;

        double result = instance.evaluateExpression();
        assertEquals(expResult, result, 0);
    }

}