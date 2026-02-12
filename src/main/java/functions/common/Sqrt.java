package functions.common;

import functions.Function;

public class Sqrt implements Function {
    @Override
    public String getName() {
        return "sqrt";
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
        return Math.sqrt(args[0]);
    }
}
