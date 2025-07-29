package Tokenizer;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TokenizerTest {

    @Test
    public void tokenize() {
        Tokenizer instance = new Tokenizer("Max(3,2,1)-Min(3,2,1)");

        List<String> result = instance.tokenize();
        List<String> expResult = Arrays.asList("max", "(", "3", ",", "2", ",", "1", ")", "-", "min", "(", "3", ",", "2", ",", "1", ")");

        System.out.println(result);
        assertEquals(expResult, result);

    }

    @Test
    public void tokenizeRuleOfSings() {
        Tokenizer instance = new Tokenizer("-7/-(-3+-3.33*4- -20/10)^2");

        List<String> expResult =
                Arrays.asList("-7", "/", "-", "(", "-3", "+", "-3.33", "*", "4", "-", "-20", "/", "10", ")", "^", "2");

        //System.out.println("Original: " +expResult);

        List<String> result = instance.tokenize();

        System.out.println(result);
        assertEquals(expResult, result);
    }

    @Test
    public void getFunctionArgCount() {
        Tokenizer instance = new Tokenizer("Max(3,2,1)");
        instance.tokenize();

        int result = Tokenizer.getFunctionArgCount("max");
        int expResult = 3;

        System.out.println(result);
        assertEquals(expResult, result);
    }


}