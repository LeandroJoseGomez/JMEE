package functions.trigonometric;

import functions.Function;

public class Cos implements Function {
    @Override
    public String getName() {
        return "cos";
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
        return Math.cos(Math.toRadians(args[0]));
    }
}
