package operators;

import builder.Builder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class OperatorTest {

    private Operator operator;

    @BeforeEach
    void setUp() {
        operator = new Operator() {
            @Override
            public String getSymbol() {
                return "%";
            }

            @Override
            public int getPrecedence() {
                return 2;
            }

            @Override
            public int getArgsCount() {
                return 2;
            }

            @Override
            public boolean isLeftAssociative() {
                return true;
            }

            @Override
            public BigDecimal execute(MathContext mathContext, BigDecimal... args) {
                return (args[0].divide(args[1], mathContext)).pow(2);
            }
        };

        OperatorRegistry.register(operator);
    }

    @Test
    void getSymbol() {
        assertEquals("%", operator.getSymbol());
    }

    @Test
    void getPrecedence() {
        assertEquals(2, operator.getPrecedence());
    }

    @Test
    void getArgsCount() {
        assertEquals(2, operator.getArgsCount());
    }

    @Test
    void isLeftAssociative() {
        assertTrue(operator.isLeftAssociative());
    }

    @Test
    void execute() {
        Builder builder = new Builder("2%2");
        builder.builExpression();
        BigDecimal result= new BigDecimal("1");
        assertEquals(result, builder.evaluate());
    }
}