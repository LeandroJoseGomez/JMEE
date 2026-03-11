package examples;

import builder.Builder;

import java.math.BigDecimal;
import java.math.MathContext;

public class ExampleBigdecimal {
    public static void main(String[] args) {

        Builder builder = new Builder("((789.1234567890123 " +
                                                "+ 456.9876543210987)^5) " +
                                                "/ (123.456789 * 0.000000789123) * (1 / 3)", new MathContext(10));

        Builder builder2 = new Builder("((789.1234567890123 " +
                "+ 456.9876543210987)^5) " +
                "/ (123.456789 * 0.000000789123) * (1 / 3)", new MathContext(100));

        builder.builExpression();
        builder2.builExpression();


        System.out.println("MathContext con 10 digitos: " +builder.evaluate());
        System.out.println("MathContext con 100 digitos: " +builder2.evaluate());

        /*
            El parametro MathContext es static, por ende al modificarlo para una expresion este se modifica globalmente
            para todas. Esto es un inconveniente porque cada expresion debe ser independiente.
         */
    }
}
