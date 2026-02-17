package evaluator;

import org.junit.jupiter.api.Test;
import parser.Parser;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EvaluatorTest {

    @Test
    void evaluateExpression() {

        List<String> psofixExpression = Arrays.asList
                                        ("-7", "-3", "-3.33", "4", "*", "+", "-20", "10", "/", "-", "2", "^", "/");
        double expResult = -0.034135950813020816;

        double result = Evaluator.evaluateExpression(psofixExpression);
        assertEquals(expResult, result);
    }

    @Test
    void evaluateExpression_2() {
        List<String> psofixExpression = Arrays.asList
                ("0", "sin", "-5.5", "-2", "abs", "*", "-");
        double expResult = 11;

        double result = Evaluator.evaluateExpression(psofixExpression);
        assertEquals(expResult, result);
    }

    @Test
    void evaluateExpression_3() {
        List<String> psofixExpression = Arrays.asList
                ("-10.5", "2", "2", "^", "-3", "1", "+", "-", "/", "2", "*");
        double expResult = -3.5;

        double result = Evaluator.evaluateExpression(psofixExpression);
        assertEquals(expResult, result);
    }

    @Test
    void evaluateExpression_4() {
        List<String> psofixExpression = Arrays.asList
                ("100", "log10", "3.5", "-1.5", "+", "2", "^", "*");
        double expResult = 400;

        double result = Evaluator.evaluateExpression(psofixExpression);
        assertEquals(expResult, result);
    }

    @Test
    void evaluateExpression_5() {
        List<String> psofixExpression = Arrays.asList
                ("2", "3", "2", "^", "^", "NEG", "500", "12", "+", "/", "1.5", "+");
        double expResult = 2.5;

        double result = Evaluator.evaluateExpression(psofixExpression);
        assertEquals(expResult, result);
    }

}