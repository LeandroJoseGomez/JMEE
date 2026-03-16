package custom;

import builder.Builder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.junit.Assert.assertEquals;

class CustomFunctionTest {

    private CustomFunction customFunction;
    private final String FUNCTION_NAME = "funSum";
    private final int DEFAULT_PRECEDENCE = 3;
    private final double[] ARGS = {3.5, 5.7};
    private final BigDecimal RESULT = new BigDecimal("9.2");

    @BeforeEach
    void setUp() {
        customFunction = new CustomFunction(FUNCTION_NAME, 2) {
            @Override
            public BigDecimal function(MathContext mathContext, BigDecimal... args) {
                return args[0].add(args[1], MathContext.UNLIMITED);
            }
        };
        customFunction.addFunction();
    }

    @Test
    void getName() {
        assertEquals(FUNCTION_NAME, customFunction.getName());
    }

    @Test
    void getArgsCount() {
        assertEquals(ARGS.length, customFunction.getArgsCount());
    }

    @Test
    void getPrecedence() {
        assertEquals(DEFAULT_PRECEDENCE, customFunction.getPrecedence());
    }

    @Test
    void execute() {
        // Este metodo usa al function(), por ende solo se tiene que probar uno de los dos.
    }

    @Test
    void function() {
        Builder builder = new Builder("funSum(3.5, 5.7)");
        builder.builExpression();

        assertEquals(RESULT, builder.evaluate());
    }

    @Test
    void addFunction() {
        // Este metodo ya esta ciendo testeado en el setUp()
    }
}