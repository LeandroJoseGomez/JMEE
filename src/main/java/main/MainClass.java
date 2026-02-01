package main;

import Builder.Builder;
import parser.CustomFunction;

public class MainClass {
    public static void main(String[] args) {

        CustomFunction customFunction = new CustomFunction("sum",2) {
            @Override
            public double function(double... arguments) {
                return arguments[0] + arguments[1];
            }
        };
        customFunction.addFunction();

        Builder builder = new Builder("sum(3,2)"); // Se escribe la expresion.  "3*x+2*y"
        System.out.println("La expresion a evaluar es: " +builder.getExpression());

        // Se setean los valores de las variables.
        //builder.setParameter("x", 2);
        //builder.setParameter("y", 3);

        builder.builExpression();// Se procesa.
        System.out.println(builder.getTokens());
        System.out.println(builder.getPosfixExpression());

        // Se evalua.
        double result = builder.evaluate();
        System.out.println("El resultado fue ==> " +result);

    }
}
