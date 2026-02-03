
package evaluator;

import parser.CustomFunction;
import parser.ExpressionHandler;

import java.util.List;
import java.util.Stack;

/**
 * Clase encargada de analizar y evaluar la expresion dada en notacion posfija, siguiendo la implementacion del
 * algoritmo Shunting Yard
 *
 * @author Leandro Gómez.
 * @version 1.0.0
 * @since 0.9.0
 */
public class Evaluator extends ExpressionHandler{

    /**
     * Metodo encargado de evaluar la expresión
     * @param posfixExpression Lista de String que contiene los tokens de la expresion en notacion posfija.
     * @return Variable double con el resultado de la expresion evaluada en notación posfija.
     * @since 1.0.0
     */
    public double evaluateExpression(List<String> posfixExpression) {
        if (posfixExpression == null) {
            throw new IllegalStateException("Se debe llamar al metodo parseExpression() antes de evaluar.");
        }

        Stack<Double> stack = new Stack<>();

        for (String token : posfixExpression) {
            if (isNumber(token)) {
                stack.push(Double.valueOf(token));
            } else if (isOperator(token)) {
                if (stack.size() < 2) throw new IllegalArgumentException("Expresión mal formada: faltan operandos para " + token);

                double b = stack.pop();
                double a = stack.pop();

                stack.push(switch (token) {
                    case "+" -> a + b;
                    case "-" -> a - b;
                    case "*" -> a * b;
                    case "/" -> {
                        if (b == 0) throw new ArithmeticException("No se puede dividir entre CERO");
                        yield a / b;
                    }
                    case "^" -> Math.pow(a, b);
                    default -> throw new UnsupportedOperationException("Operador no soportado: " + token);
                });
            } else if (isFunction(token)) {
                if (stack.isEmpty()) throw new IllegalArgumentException("Expresión mal formada: falta argumento para la función " + token);

                double val = stack.pop();
                stack.push(evaluateFunction(token, val));
            } else
                if (customFunctions.containsKey(token)) {
                    CustomFunction func = customFunctions.get(token);
                    int numArgs = func.getFunctionArgument();


                    double[] args = new double[numArgs];
                        for (int i = numArgs - 1; i >= 0; i--) {
                            if (stack.isEmpty()) throw new IllegalArgumentException("Faltan argumentos para: " + token);
                            args[i] = stack.pop();
                        }

                stack.push(func.function(args));
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("La expresión es inválida: sobran operandos.");
        }


        return stack.pop(); // Resultado de la operacion.
    }

    private double evaluateFunction(String function, double value) {
        return switch (function) {
            case "sqrt" -> Functions.sqrt(value);
            case "sin"  -> Functions.sin(value);
            case "cos"  -> Functions.cos(value);
            case "tan"  -> Functions.tan(value);
            case "log"  -> Functions.log(value);
            case "ln"   -> Functions.ln(value);
            default     -> throw new UnsupportedOperationException("Función desconocida: " + function);
        };
    }
}
