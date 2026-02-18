package operators.common;

import operators.Operator;

/**
 * @author Leandro Gómez.
 * @version 1.1.0
 * @since 1.1.0
 */
public class Exponentiation implements Operator {
    @Override
    public String getSymbol() {
        return "^";
    }

    @Override
    public int getPrecedence() {
        return 1;
    }

    @Override
    public int getArgsCount() {
        return 2;
    }

    @Override
    public boolean isLeftAssociative() {
        return false;
    }

    @Override
    public double execute(double... args) {
        return Math.pow(args[0], args[1]);
    }
}
