package parser;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionHandlerTest {

    @Test
    public void isToken() {
    }

    @Test
    public void isNumber() {
    }

    @Test
    public void isOperator() {
        System.out.println("test_isOperator");

        ExpressionHandler instance = new ExpressionHandler();
        String token = "tan";

        boolean expResult = false;
        boolean result = instance.isOperator(token);
        assertEquals(expResult, result);

        token = "+";
        expResult = true;
        result = instance.isOperator(token);
        assertEquals(expResult, result);

        token = "x";
        expResult = true;
        result = instance.isOperator(token);
        assertEquals(expResult, result);

        token = ".";
        expResult = false;
        result = instance.isOperator(token);
        assertEquals(expResult, result);
    }

    @Test
    public void isFunction() {
    }
}