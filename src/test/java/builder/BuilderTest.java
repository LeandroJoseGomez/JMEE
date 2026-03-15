package builder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class BuilderTest {

    private final String expression = "3*x^y";
    private final List<String> tokens = Arrays.asList("3", "*", "x", "^", "y");
    private final List<String> posfixExpression = Arrays.asList("3", "5", "2", "^", "*");
    BigDecimal result;
    private Builder builder;

    @BeforeEach
    void setUp() {
        builder = new Builder(expression);
    }

    @Test
    void getExpression() {
        assertEquals(expression, builder.getExpression());
    }

    @Test
    void getTokens() {
        builder.builExpression();

        assertEquals(tokens, builder.getTokens());
    }

    @Test
    void getPosfixExpression() {
        /*
         * Es necesario indicar las variables y su respectivo valor antes de
         * parsear la expresión debido a que el parser obvia cualquier simbolo desconocido.
         */
        builder.setParameter("x", 5);
        builder.setParameter("y", 2);
        builder.builExpression();

        assertEquals(posfixExpression, builder.getPosfixExpression());
    }

    @Test
    void setParameter() {
        builder.setParameter("x", 2);
        builder.setParameter("y", 5);

        assertEquals(2, builder.getParameterValue("x").intValue());
        assertEquals(5, builder.getParameterValue("y").intValue());
    }

    @Test
    void getParameterValue() {
        builder.setParameter("x", 3);
        builder.setParameter("y", 4);

        assertEquals(3, builder.getParameterValue("x").intValue());
        assertEquals(4, builder.getParameterValue("y").intValue());
    }

    @Test
    void builExpression() {
        builder.setParameter("x", 5);
        builder.setParameter("y", 2);
        builder.builExpression();

        assertEquals(tokens, builder.getTokens());
        assertEquals(posfixExpression, builder.getPosfixExpression());
    }

    @Test
    void evaluate() {
        builder.setParameter("x", 5);
        builder.setParameter("y", 2);
        result  = new BigDecimal(75);
        builder.builExpression();

        assertTrue(result.compareTo(builder.evaluate()) == 0);
    }

}