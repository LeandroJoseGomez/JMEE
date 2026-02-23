package examples;

import builder.Builder;
import custom.CustomFunction;

public class ExampleExpression {
    public static void main(String[] args) {

        // Creacion de funciones personalizadas.
        CustomFunction customFunction = new CustomFunction("sum",2) {
            @Override
            public double function(double... arguments) {
                return arguments[0] + arguments[1];
            }
        };
        customFunction.addFunction();

        CustomFunction customFunction2 = new CustomFunction("min",2) {
            @Override
            public double function(double... arguments) {
                return arguments[0] - arguments[1];
            }
        };
        customFunction2.addFunction();

        Builder builder = new Builder("2^3^2"); //  sum(3,2) * min(10,5)

        // Se setean los valores de las variables.
        //builder.setParameter("x", 10);
        //builder.setParameter("y", 5);

        builder.builExpression();// Se procesa.

        // Se evalua.
        double result = builder.evaluate();

        // Se muestra.
        System.out.println("Expresion a evaluar => " +builder.getExpression());
        System.out.println("Tokens => " + builder.getTokens());
        System.out.println("Tokens en notacion posfija => " + builder.getPosfixExpression());
        System.out.println("El resultado fue => " +result);
    }
}
