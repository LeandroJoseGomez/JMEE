package examples;

import builder.Builder;
import custom.CustomFunction;

import java.math.BigDecimal;

public class ExampleExpression {
    public static void main(String[] args) {

        Builder builder = new Builder("2+2"); //  sum(3,2) * min(10,5)
        Builder builder2 = new Builder("1+1");

        // Se setean los valores de las variables.
        //builder.setParameter("x", 10);
        //builder.setParameter("y", 5);

        builder.builExpression();// Se procesa.
        builder2.builExpression();

        // Se evalua.
        BigDecimal result = builder.evaluate();

        // Se muestra.
        System.out.println("Expresion a evaluar => " +builder.getExpression());
        System.out.println("Tokens => " + builder.getTokens());
        System.out.println("Tokens en notacion posfija => " + builder.getPosfixExpression());
        System.out.println("El resultado fue => " +result);
    }
}
