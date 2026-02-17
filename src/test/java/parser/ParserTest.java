package parser;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest extends ExpressionHandler {

    @Test
    void infixToPostfix() {
        List<String> posfixExpression = Arrays.asList("-3", "+", "-5", "*", "sin", "(", "90", ")");
        List<String> expResult = Arrays.asList("-3", "-5", "90", "sin", "*", "+");

        List<String> result = Parser.infixToPostfix(posfixExpression);
        assertEquals(expResult, result);
    }

    @Test
    void infixToPostfix_2() {
        List<String> posfixExpression = Arrays.asList("2", "^", "3", "^", "2");
        List<String> expResult = Arrays.asList("2", "3", "2", "^", "^");

        List<String> result = Parser.infixToPostfix(posfixExpression);
        assertEquals(expResult, result);
    }

    @Test
    void infixToPostfix_3() {
        List<String> posfixExpression = Arrays.asList("cos", "(", "40", "+", "5", ")", "*", "3");
        List<String> expResult = Arrays.asList("40", "5", "+", "cos", "3", "*");

        List<String> result = Parser.infixToPostfix(posfixExpression);
        assertEquals(expResult, result);
    }

    @Test
    void infixToPostfix_4() {
        List<String> posfixExpression = Arrays.asList
                ("sin", "(", "90", ")", "+", "cos", "(", "90", ")", "+", "tan", "(", "90", ")" );

        List<String> expResult = Arrays.asList("90", "sin", "90", "cos", "+",  "90", "tan", "+");

        List<String> result = Parser.infixToPostfix(posfixExpression);
        assertEquals(expResult, result);
    }

    @Test
    void infixToPostfix_5() {
        List<String> posfixExpression = Arrays.asList("(", "10", "-", "2", ")", "*", "5", "^", "2");
        List<String> expResult = Arrays.asList("10", "2", "-", "5", "2", "^", "*");

        List<String> result = Parser.infixToPostfix(posfixExpression);
        assertEquals(expResult, result);
    }
}