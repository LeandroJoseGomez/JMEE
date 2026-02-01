/**
 * Clase padre la cual gestiona las precedencias de los operadores y funciones al igual que los metodos de verificación.
 */
package parser;

import java.util.HashMap;

/**
 *
 * @author Leandro Gómez.
 * @version 1.1.2
 */
public class ExpressionHandler {

    protected static HashMap<String, Integer> precedence = new HashMap<>();
    protected static HashMap<String, Double> variables = new HashMap<>();
    protected static HashMap<String, CustomFunction> customFunctions = new HashMap<>();

    // Bloque estatico de inicialización para las precedencias.
    static {
        precedence.put("+", 1);
        precedence.put("-", 1);
        precedence.put("*", 2);
        precedence.put("/", 2);
        precedence.put("^", 3);
        precedence.put("sqrt", 3);

        precedence.put("sin", 4);
        precedence.put("cos", 4);
        precedence.put("tan", 4);
        precedence.put("csc", 4);
        precedence.put("sec", 4);
        precedence.put("cot", 4);

        precedence.put("log", 4);
        precedence.put("ln", 4);
    }

    /**
     * Verifica si es un token reconocido.
     * @param token token a reconocer.
     * @return TRUE en caso de que si exista y FALSE en caso de que no exista.
     */
    protected boolean isToken(String token){
        return precedence.containsKey(token);
    }

    /**
     * Verifica si el token en cuestión es un número.
     * @param token Posible signo, número o función de la expresión a evaluar.
     * @return TRUE en caso de que sea un número y FALSE en caso de que no lo sea.
     */
    protected boolean isNumber(String token) {
        try {
            Double.valueOf(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Verifica si el token en cuestión es un operador aritmetico ordinario.
     * @param token Posible signo, número o función de la expresión a evaluar.
     * @return TRUE en caso de que sea un operador y FALSE en caso de que no lo sea.
     */
    protected boolean isOperator(String token) {
        precedence.containsKey(token);

        return switch (token) {
            case "+" ->
                    true;
            case "-" ->
                    true;
            case "*" ->
                    true;
            case "/" ->
                    true;
            case "^" ->
                    true;
            default ->
                    false;
        };
    }

    /**
     * Verifica si la función en cuestión está dentro de las reconosidas.
     * @param function Función de la expresión a evaluar.
     * @return TRUE en caso de que sea una función y FALSE en caso de que no lo sea.
     */
    protected static boolean isFunction(String function) {
        return switch (function) {
            case "sqrt" ->
                    true;

            // Trigonometricas.
            case "sin" ->
                    true;
            case "cos" ->
                    true;
            case "tan" ->
                    true;

            // Logaritmicas.
            case "log" ->
                    true;
            case "ln" ->
                    true;

            default ->
                    false;
        };
    }
}
