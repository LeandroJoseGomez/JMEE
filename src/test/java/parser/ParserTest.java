package parser;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void infixToPostfix() { //               -3", "+", "-5", "*", "sin", "(", "30", ")

        List<String> expression =
                Arrays.asList
                        ("-7", "/", "-", "(", "-3", "-", "3.33", "*", "4", "+", "20", "/", "10", ")", "^", "2");

        //Parser parser = new Parser(expression);

        List<String> expResult =
                Arrays.asList("-3", "-5", "30", "sin", "*", "+");

       // List<String> result = parser.infixToPostfix();
        //System.out.println(result);
        //assertEquals(expResult, result);
    }

    @Test
    public void infixToPostfix2() {
        List<String> expression =
                Arrays.asList
                        ("-3", "^", "2");

        //Parser parser = new Parser(expression);

        List<String> expResult =
                Arrays.asList("-3", "2", "^");

        //List<String> result = parser.infixToPostfix();
       // assertEquals(expResult, result);
    }
}