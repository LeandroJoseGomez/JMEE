/**
 * Clase encargada del formateo y tokenización de la expresión original utilizando un algoritmo propio titulado Sorting Buffer.
 */
package Tokenizer;

import parser.ExpressionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Leandro Gómez.
 * @version 1.0.0
 */
public class Tokenizer extends ExpressionHandler {

    private static String expression = "";

    // Variable auxiliar para determinar cuando el signo '-' es de uso unario.
    private char unaryMinus = ' ';

    // Funciones con multiples argumentos <función, número de argumentos>.
    private static HashMap<String, Integer> functionArgCount = new HashMap<>();

    /**
     * Constructor de clase.
     * @param expression Expresión a evaluar.
     * @since 1.0.0
     */
    public Tokenizer(String expression) {
        this.expression = expression;
    }

    // Formatea la expresión para una mejor manipulación.
    private String formatter(String expression) {
        expression = expression.replaceAll("\\s+", "");
        expression = expression.toLowerCase();

        return expression;
    }

    /**
     * Divide y tokeniza la expresión haciendo uso del algoritmo Sorting Buffer.
     * @return Lista que contiene la expresión original ya tokenizada.
     * @since 1.0.0
     */
    public List<String> tokenize() {

        expression = formatter(expression);

        List<String> tokens = new ArrayList<>();
        int length = expression.length();


        for (int i = 0; i < length; i++) {
            char currentChar = expression.charAt(i);
            char prevChar = (i !=0 ) ? expression.charAt(i-1) : ' ';
            char nextChar = (i != length-1 ) ? expression.charAt(i+1) : ' ';

            // Buffer para números.
            if (Character.isDigit(currentChar)) {
                StringBuilder buffer = new StringBuilder();

                // Verificar si existe un signo unitario (-) asociado al numero para agregarlo al buffer.
                if (i != 0 && prevChar == unaryMinus){
                    buffer.append(unaryMinus);
                    unaryMinus = ' '; // reseteo.
                }

                // Agregar todos los digitos y posibles puntos decimales al buffer.
                while (i < length && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    buffer.append(expression.charAt(i));
                    i++;
                }

                tokens.add(buffer.toString());
                i--; // Retrocede para no saltar el sigiente caracter.

                // Buffer para funciones.
            } else if (Character.isLetter(currentChar)) {
                StringBuilder buffer = new StringBuilder();

                while (i < length && (Character.isLetter(currentChar))) {
                    buffer.append(currentChar);
                    i++;
                    currentChar = expression.charAt(i);
                }

                tokens.add(buffer.toString());
                i--;

            } else if (currentChar == '-'){

                 // Primer bloque de validación (El caracter siguiente es un numero).
                 if (Character.isDigit(nextChar)){
                    if (i > 0 && prevChar == currentChar){
                        tokens.add("+");

                    } else if (i > 0 && prevChar == '+'){
                        tokens.remove(tokens.size()-1);
                        tokens.add("-");

                    } else if (!Character.isDigit(prevChar)){
                        unaryMinus = '-';

                    }

                    // Segundo bloque de validación (regla de los signos II).
                } else if ( !Character.isDigit(prevChar) && prevChar != '(' && prevChar != ')' || Character.isLetter(nextChar)){

                    if (prevChar == currentChar){
                        tokens.add("+");

                    }else if (prevChar == '+'){
                        tokens.remove(tokens.size()-1);
                        tokens.add("-");

                    }else if (nextChar == '+'){
                        tokens.add("-");
                        i++;

                    }else if (nextChar == '-'){
                        tokens.add("+");
                        i++;

                    } else{
                        tokens.add("-");
                    }

                }

                // Los tokens no comparados como por ejemplo los parentesis se agregan directamente.
                } else{
                    tokens.add(String.valueOf(currentChar));
                }
        }

        // Verifica las funciones con multiples argumentos.
        countFunctionArguments(tokens);
        return tokens;
    }

    /*
    * Guarda las funciones y sus multiples argumentos (Falta agregar una implementacion mas optimizada).
     */
    private void countFunctionArguments(List<String> tokens){

        String function = "";
        int argCount = 0; // Total de argumentos de cada funcion.

        for(String token : tokens){
            if (isFunction(token)) {
                function = token;
                argCount++;
            }else if (token.equals(",")) {
                argCount++;

            }else if (token.equals(")")) {
                functionArgCount.put(function, argCount);
                argCount = 0;
            }
        }
    }

    // Retorna la cantidad de argumentos de una función.
    public static int getFunctionArgCount(String key){
        return functionArgCount.get(key);
    }
}
