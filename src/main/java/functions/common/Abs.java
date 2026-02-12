package functions.common;

import functions.Function;

public class Abs implements Function {
    @Override
    public String getName() {
        return "abs";
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
        return Math.abs(args[0]);
    }
}
