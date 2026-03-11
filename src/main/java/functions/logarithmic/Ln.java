package functions.logarithmic;

import ch.obermuhlner.math.big.BigDecimalMath;
import functions.Function;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author Leandro J. Gómez.
 * @version 1.1.0
 * @since 1.2.0
 */
public class Ln implements Function {
    @Override
    public String getName() {
        return "ln";
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
        return BigDecimalMath.log(args[0], mathContext);
    }
}
