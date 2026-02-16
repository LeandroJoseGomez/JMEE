package tokenizer;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TokenizerTest {

    @Test
    public void tokenize() {
        List<String> result = Tokenizer.tokenize("10>=5");
        List<String> expResult = Arrays.asList("sin", "(", "x", ")", "-", "cos", "(", "y", ")");

        System.out.println(result); // Solo para uso interno.
        assertEquals(expResult, result);

    }

    @Test
    public void tokenizeRuleOfSings() {
        List<String> expResult =
                Arrays.asList("-7", "/", "-", "(", "-3", "+", "-3.33", "*", "4", "-", "-20", "/", "10", ")", "^", "2");

        //System.out.println("Original: " +expResult);

        List<String> result = Tokenizer.tokenize("-7/-(-3+-3.33*4- -20/10)^2");

        System.out.println(result); // Solo para uso interno.
        assertEquals(expResult, result);
    }


}