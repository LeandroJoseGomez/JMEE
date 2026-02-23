package functions.trigonometric;

import functions.Function;

/**
 * @author Leandro J. Gómez.
 * @version 1.1.0
 * @since 1.2.0
 */
public class Tan implements Function {
    @Override
    public String getName() {
        return "tan";
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
    public double execute(double... args) {
        return Math.tan(Math.toRadians(args[0]));
    }
}
