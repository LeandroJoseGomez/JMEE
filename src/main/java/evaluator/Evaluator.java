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


/**
 *
 * @author Leandro Gómez.
 * @version 1.1.2
 */
public class Evaluator extends ExpressionHandler{

    private List<String> posfixExpression;
    private String expression;
    private double ans = 0;

    /**
     * Constructor de clase.
     * @param expression Expresión en notación infija.
     */
    public Evaluator(String expression) {
        this.expression = expression;
    }

    public void parseExpression() {
        List<String> tokens = Tokenizer.tokenize(expression);
        Parser parser = new Parser(tokens);
        this.posfixExpression = parser.infixToPostfix();
        System.out.println(tokens.toString());
        System.out.println(posfixExpression);
    }

    public void setParameter(String parameter, double value){
        variables.put(parameter, value);
    }

    /**
     * Metodo encargado de evaluar la expresión.
     * @return Retorna el resultado de la expresion evaluada en notación posfija.
     * @since 1.0.0
     */
    public double evaluateExpression() {
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
                    case "/" -> a / b;
                    case "^" -> Math.pow(a, b);
                    default -> throw new UnsupportedOperationException("Operador no soportado: " + token);
                });
            } else if (isFunction(token)) {
                if (stack.isEmpty()) throw new IllegalArgumentException("Expresión mal formada: falta argumento para la función " + token);

                double val = stack.pop();
                stack.push(evaluateFunction(token, val));
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("La expresión es inválida: sobran operandos.");
        }

        this.ans = stack.pop();
        return this.ans;
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
