package examples;

import builder.Builder;
import custom.CustomFunction;

import java.math.BigDecimal;

public class ExampleExpression {
    public static void main(String[] args) {

        Builder builder = new Builder("x+y"); //  sum(3,2) * min(10,5)
        builder.setParameter("x", 10);
        builder.setParameter("y", 2);

        Builder builder2 = new Builder("x*y");
        builder2.setParameter("x", 1);
        builder2.setParameter("y", 5);

        builder.builExpression();// Se procesa.
        builder2.builExpression();

        // Se evalua.
        BigDecimal result = builder.evaluate();
        BigDecimal result2 = builder2.evaluate();

        System.out.println("El resultado fue => " +result);
        System.out.println("El resultado fue => " +result2);
    }



    // Casos de multiplicacion implicita:

    // 2(10)2 --> 2*10*2
    // 2xy --> 2*x*y
}
