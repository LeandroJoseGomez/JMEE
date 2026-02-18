package functions.common;

import functions.Function;

/**
 * @author Leandro Gómez.
 * @version 1.1.0
 * @since 1.1.0
 */
public class Pow implements Function {
    @Override
    public String getName() {
        return "pow";
    }

    @Override
    public int getArgsCount() {
        return 2;
    }

    @Override
    public int getPrecedence() {
        return 3;
    }

    @Override
    public double execute(double... args) {
        return Math.pow(args[0], args[1]);
    }
}
