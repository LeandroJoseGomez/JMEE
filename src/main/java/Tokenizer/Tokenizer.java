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

    // Variable auxiliar para determinar cuando el signo '-' esta asociado a un numero.
    private char unaryMinus = ' ';

    // Contenedor para funciones con multiples argumentos <función, número de argumentos>.
    private static HashMap<String, Integer> functionArgCount = new HashMap<>();

    /**
     * Constructor de clase.
     * @param expression Expresión a evaluar.
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

            // Verifica que exista un signo unitario asociado a un numero.
            } else if(currentChar == '-'){
                if (Character.isDigit(nextChar) && !Character.isDigit(prevChar)){
                    unaryMinus = '-';
                } else{
                    tokens.add(String.valueOf(currentChar));
                }

                // Los tokens no comparados como por ejemplo los parentesis se agregan directamente.
            } else{
                tokens.add(String.valueOf(currentChar));
            }

        }

        // Verificar las funciones con multiples argumentos.
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

    /**
     * Retorna la cantidad de argumentos de una función.
     *
     * @param key Funcion de la que se quiere saver su cantidad de parametros.
     * @return Retorna un valor entero que indica la cantidad de argumentos.
     */
    public static int getFunctionArgCount(String key){
        return functionArgCount.get(key);
    }
}
