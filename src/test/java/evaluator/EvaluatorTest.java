package evaluator;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EvaluatorTest {
    private List<String> posfixExpression;
    BigDecimal expResult;

    @Test
    void evaluateExpression() {

        posfixExpression = Arrays.asList
                                        ("-7", "-3", "-3.33", "4", "*", "+", "-20", "10", "/", "-", "2", "^", "/");
        expResult = new BigDecimal("-0.03413595081302081707811866046627758");

        assertEquals(expResult, new Evaluator().evaluateExpression(posfixExpression, new MathContext(100)));
    }

    @Test
    void evaluateExpression_2() {
        posfixExpression = Arrays.asList("0", "sin", "-5.5", "-2", "abs", "*", "-");
        expResult = new BigDecimal("11");

        BigDecimal result = new Evaluator().evaluateExpression(posfixExpression, new MathContext(100));
        assertTrue(expResult.compareTo(result) == 0);
    }

    @Test
    void evaluateExpression_3() {
        posfixExpression = Arrays.asList("-10.5", "2", "2", "^", "-3", "1", "+", "-", "/", "2", "*");
        expResult = new BigDecimal("-3.5");

        BigDecimal result = new Evaluator().evaluateExpression(posfixExpression, new MathContext(100));
        assertTrue(expResult.compareTo(result) == 0);
    }

    @Test
    void evaluateExpression_4() {
        posfixExpression = Arrays.asList("100", "log10", "3.5", "-1.5", "+", "2", "^", "*");
        expResult = new BigDecimal("400");

        BigDecimal result = new Evaluator().evaluateExpression(posfixExpression, new MathContext(100));
        assertTrue(expResult.compareTo(result) == 0);
    }

    @Test
    void evaluateExpression_5() {
        List<String> psofixExpression = Arrays.asList
                ("2", "3", "2", "^", "^", "500", "12", "+", "/", "1.5", "+");
        expResult = new BigDecimal("2.5");

        BigDecimal result = new Evaluator().evaluateExpression(posfixExpression, new MathContext(100));
        assertTrue(expResult.compareTo(result) == 0);
    }

}