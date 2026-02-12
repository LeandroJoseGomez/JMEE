package functions.logarithmic;

import functions.Function;

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
    public double execute(double... args) {
        return Math.log(args[0]);
    }
}
