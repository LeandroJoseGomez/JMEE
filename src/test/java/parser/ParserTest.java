package parser;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void infixToPostfix() {
        System.out.println("test_infixToPostfix");

        List<String> expression =
                Arrays.asList
                        ("-3", "+", "-5", "*", "sin", "(", "30", ")");

        Parser parser = new Parser(expression);

        List<String> expResult =
                Arrays.asList("3", "5", "30", "sin", "*", "+");

        List<String> result = parser.infixToPostfix();
        assertEquals(expResult, result);
    }

    @Test
    public void infixToPostfix2() {
        List<String> expression =
                Arrays.asList
                        ("-3", "^", "2");

        Parser parser = new Parser(expression);

        List<String> expResult =
                Arrays.asList("-3", "2", "^");

        List<String> result = parser.infixToPostfix();
        assertEquals(expResult, result);
    }
}