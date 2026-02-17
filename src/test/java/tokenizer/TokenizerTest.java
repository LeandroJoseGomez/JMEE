package tokenizer;

import org.junit.jupiter.api.Test;
import parser.ExpressionHandler;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

class TokenizerTest extends ExpressionHandler {

    @Test
    void tokenize() {
        List<String> tokens = Tokenizer.tokenize("3+2 * 5^2");
        List<String> expResult = Arrays.asList("3", "+", "2", "*", "5", "^", "2");
        assertEquals(expResult, tokens);
    }

    @Test
    void tokenize_2() {
        List<String> tokens = Tokenizer.tokenize("Sin(X)-cOs(y)");
        List<String> expResult = Arrays.asList("sin", "(", "x", ")", "-", "cos", "(", "y", ")");
        assertEquals(expResult, tokens);
    }

    @Test
    void tokenize_3() {

        List<String> tokens = Tokenizer.tokenize("-7/-(-3+-3.33*4- -20/10)^2");
        List<String> expResult =
                Arrays.asList("-7", "/", "-", "(", "-3", "+", "-3.33", "*", "4", "-", "-20", "/", "10", ")", "^", "2");

        assertEquals(expResult, tokens);
    }

    @Test
    void tokenize_4() {
        List<String> tokens = Tokenizer.tokenize("sin(pi / 2)");
        List<String> expResult = Arrays.asList("sin", "(", "pi", "/", "2", ")");
        assertEquals(expResult, tokens);
    }

    @Test
    void tokenize_5() {
        List<String> tokens = Tokenizer.tokenize("4 * -abs(-5)");
        List<String> expResult = Arrays.asList("4", "*", "-", "abs", "(", "-5", ")");
        assertEquals(expResult, tokens);
    }

}