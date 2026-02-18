package operators.common;

import operators.Operator;

/**
 * @author Leandro Gómez.
 * @version 1.1.0
 * @since 1.1.0
 */
public class Addition implements Operator {
    @Override
    public String getSymbol() {
        return "+";
    }

    @Override
    public int getPrecedence() {
        return 0;
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
    public double execute(double... args) {
        return args[0] + args[1];
    }
}
