package examples;

import builder.Builder;
import custom.CustomFunction;

import java.math.BigDecimal;
import java.math.MathContext;

public class CustomFunctions {
    public static void main(String[] args) {

        // Creacion de funciones personalizadas.
        CustomFunction customFunction = new CustomFunction("sum",2) {

            @Override
            public BigDecimal function(MathContext mathContext, BigDecimal... args) {
                return args[0].add(args[1], mathContext);
            }

        };
        customFunction.addFunction();

        CustomFunction customFunction2 = new CustomFunction("min",2) {

            @Override
            public BigDecimal function(MathContext mathContext, BigDecimal... args) {
                return args[0].subtract(args[1], mathContext);
            }

        };
        customFunction2.addFunction();


        Builder builder = new Builder("sum(0.5, 0.001) * min(13.03, 0.1)", MathContext.DECIMAL128);
        builder.builExpression();
        System.out.println(builder.evaluate());
    }
}
