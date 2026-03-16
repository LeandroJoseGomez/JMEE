package functions;

import builder.Builder;
import ch.obermuhlner.math.big.BigDecimalMath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.junit.Assert.assertEquals;

class FunctionTest {
    Function function;

    @BeforeEach
    void setUp() {
        function = new Function() {
            @Override
            public String getName() {
                return "log2";
            }

            @Override
            public int getArgsCount() {
                return 1;
            }

            @Override
            public int getPrecedence() {
                return 3;
            }

            @Override
            public BigDecimal execute(MathContext mathContext, BigDecimal... args) {
                return BigDecimalMath.log2(args[0], mathContext);
            }
        };
        FunctionRegistry.register(function);// Necesario.
    }

    @Test
    void getName() {
        assertEquals("log2", function.getName());
    }

    @Test
    void getArgsCount() {
        assertEquals(1, function.getArgsCount());
    }

    @Test
    void getPrecedence() {
        assertEquals(3, function.getPrecedence());
    }

    @Test
    void execute() {
        Builder builder = new Builder("log2(10)");
        builder.builExpression();
        BigDecimal result= new BigDecimal("3.321928094887362347870319429489390");
        assertEquals(result, builder.evaluate());
    }
}