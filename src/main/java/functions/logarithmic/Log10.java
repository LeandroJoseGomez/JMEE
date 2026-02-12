package functions.logarithmic;

import functions.Function;

public class Log10 implements Function {
    @Override
    public String getName() {
        return "log";
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
        return Math.log10(args[0]);
    }
}
