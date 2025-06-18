package Tokenizer;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TokenizerTest {

    @Test
    public void tokenize() { // + - 5.20 + ( -3.60 ) * -( sin(90) ) + -4 / - -max( -6, -7 ) + -(8)
        Tokenizer instance = new Tokenizer("7/-(3+3*4-20/10)^2"); // 3+max(4,3,2,1)+3-min(4,3,2,1)
        //

        List<String> result = instance.tokenize();
        System.out.println(result);

        List<String> expResult =
                Arrays.asList
                        ("-5.20", "+", "(", "-3.60", ")", "*", "UMinus", "(", "sin", "(", "90", ")", ")", "-4", "/", "UMinus", "+", "max", "(", "-6", ",", "-7", ")", "-", "(", "8", ")");

        //assertEquals(expResult, result);

    }

    @Test
    public void tokenizeRuleOfSings() {
        Tokenizer instance;

        instance = new Tokenizer("--");
        List<String> result = instance.tokenize();
        List<String> expResult = Arrays.asList("+");
        assertEquals(expResult, result);

        instance = new Tokenizer("+-");
        result = instance.tokenize();
        expResult = Arrays.asList("-");
        assertEquals(expResult, result);
    }

    @Test
    public void tokenize3() {
        Tokenizer instance = new Tokenizer("-1*1");////
        List<String> result = instance.tokenize();
        System.out.println(result);

        List<String> expResult = Arrays.asList("1", "*", "1");

        assertEquals(expResult, result);

    }

    @Test
    public void tokenize4() {
        Tokenizer instance = new Tokenizer("*-+");
        List<String> result = instance.tokenize();
        System.out.println(result);

        List<String> expResult = Arrays.asList("*", "-");

        assertEquals(expResult, result);

    }

    @Test
    public void tokenize5() {
        Tokenizer instance = new Tokenizer("*--");
        List<String> result = instance.tokenize();
        System.out.println(result);

        List<String> expResult = Arrays.asList("*", "+");

        assertEquals(expResult, result);

    }


    @Test
    public void getFunctionArgCount() {
    }


}