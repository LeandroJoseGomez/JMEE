/**
 * Clase encargada de evaluar la expresión matematica en notación posfija
 * siguiendo la implementación del algoritmo Shunting Yard.
 */
package evaluator;

import parser.ExpressionHandler;
import java.util.List;
import java.util.Stack;
import parser.Parser;
import Tokenizer.Tokenizer;
import java.util.ArrayList;


/**
 *
 * @author Leandro Gómez.
 * @version 1.0.0
 */
public class Evaluator extends ExpressionHandler{

    private static List<String> posfixExpression;
    private double ans = 0;
    private Tokenizer tokenizer;
    private Parser parser;

    /**
     *
     * @param expression Expresión en notación infija.
     * @since 1.0.0
     */
    public Evaluator(String expression) {
        tokenizer = new Tokenizer(expression);
        parser = new Parser(tokenizer.tokenize());
        posfixExpression = parser.infixToPostfix();
        System.out.println("Posfija" +posfixExpression);
        System.out.println("Tokens" +tokenizer.tokenize());
    }

    // Constructor vacio.
    public Evaluator(){}

    /**
     * Metodo get para optener el resultado de las operaciones.
     * @return Resultado de las operaciones.
     */
    public double getAns(){
        return ans;
    }

    /**
     * Metodo encargado de evaluar la expresión.
     * @return Retorna el resultado de la expresion evaluada en notación posfija.
     * @since 1.0.0
     */
    public double evaluateExpression() {
        Stack<Double> output = new Stack<>();

        for (String token : posfixExpression) {
            if (isNumber(token)) {
                output.push(Double.valueOf(token));
            } else if (isOperator(token)) {

                double b = output.pop();
                double a = output.pop();

                switch (token) {
                    case "+" ->
                            output.push(a + b);
                    case "-" ->
                            output.push(a - b);
                    case "*" ->
                            output.push(a * b);
                    case "/" ->
                            output.push(a / b);
                    case "^" ->
                            output.push(Math.pow(a, b));
                    default ->
                            throw new AssertionError();
                }
            } else if (isFunction(token)) {

                switch (token) {
                    case "sqrt":
                        output.push(Math.sqrt(output.pop()));
                        break;

                    case "sin":
                        output.push(Math.sin(Math.toRadians(output.pop())));
                        break;
                    case "cos":
                        output.push(Math.cos(Math.toRadians(output.pop())));
                        break;
                    case "tan":
                        output.push(Math.tan(Math.toRadians(output.pop())));
                        break;
                    case "csc":
                        output.push(1 / Math.sin(Math.toRadians(output.pop())));
                        break;
                    case "sec":
                        output.push(1 / Math.cos(Math.toRadians(output.pop())));
                        break;
                    case "cot":
                        output.push(1 / Math.tan(Math.toRadians(output.pop())));
                        break;

                        // Error de cálculo en las funciones inversas.
                    case "arcsin":
                        output.push(Math.asin(Math.toRadians(output.pop())));
                        break;
                    case "arccos":
                        output.push(Math.acos(Math.toRadians(output.pop())));
                        break;
                    case "arctan":
                        output.push(Math.atan(Math.toRadians(output.pop())));
                        break;

                    // Funciones logarítmicas.
                    case "log":
                        output.push(Math.log10(output.pop()));
                        break;
                    case "ln":
                        output.push(Math.log(output.pop()));
                        break;

                    // Funciones estadísticas.
                    case "max":
                        List<Double> numsMax = new ArrayList<>();
                        for (int i = 0; i < Tokenizer.getFunctionArgCount("max"); i++) {
                            numsMax.add(output.pop());
                        }

                        output.push(findMax(numsMax));
                        break;

                    case "min":
                        List<Double> nums = new ArrayList<>();
                        for (int i = 0; i < Tokenizer.getFunctionArgCount("min"); i++) {
                            nums.add(output.pop());
                        }
                        output.push(findMin(nums));
                        break;

                    default:
                        throw new AssertionError();
                }

            }
        }// fin foreach.

        ans = output.pop();
        return ans;
    }

    public double findMax(List<Double> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("La lista no puede estar vacía o ser nula.");
        }

        double max = Double.NEGATIVE_INFINITY; // Inicializamos con el menor valor posible
        for (double num : numbers) {
            if (num > max) {
                max = num; // Actualizamos el máximo si encontramos un número mayor

            }
        }
        return max;
    }

    public double findMin(List<Double> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("La lista no puede estar vacía o ser nula.");
        }

        double min = Double.POSITIVE_INFINITY; // Inicializamos con el mayor valor posible
        for (double num : numbers) {
            if (num < min) {
                min = num; // Actualizamos el mínimo si encontramos un número menor
            }
        }
        return min;
    }

}
