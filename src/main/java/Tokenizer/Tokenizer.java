/**
 * Clase encargada del formateo y tokenización de la expresión original utilizando
 * un algoritmo propio titulado Sorting Buffer.
 */
package Tokenizer;

import parser.ExpressionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Leandro Gómez.
 * @version 1.0.0
 */
public class Tokenizer extends ExpressionHandler {

    private static String expression = "";

    /*
    * Variable auxiliar para determinar cuando el signo '-' es de uso unario,
    * sustituyéndolo en la lista de tokens por el identificador "UMinus".
    * Expresión: "-min(4,3,2,1)" ; Resultado: [UMinus, min, (, 4, ,, 3, ,, 2, ,, 1, )].
     */
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

                // Verificar si existe un signo unitario asociado al numero para agregarlo al buffer.
                if (i != 0 && prevChar == unaryMinus){
                    buffer.append(unaryMinus);
                    unaryMinus = ' '; // reseteo.
                }

                // Agregar todos los digitos y posibles puntos decimales al buffer.
                while (i < length && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    buffer.append(expression.charAt(i));
                    i++;
                    //currentChar = expression.charAt(i);

                    //i++; // Salta al proximo caracter para verificar si sigue cumpliendo la condición.
                }

                tokens.add(buffer.toString());
                i--; // Retrocede para no saltar el sigiente caracter.

                // Buffer para funciones.
            } else if (Character.isLetter(currentChar)) {
                StringBuilder buffer = new StringBuilder();

                // Verificar si la función esta asociada a un signo unitario.
                if (unaryMinus == '-'){
                    tokens.add("UMinus");
                    unaryMinus = ' '; // reseteo.
                }

                while (i < length && (Character.isLetter(currentChar))) {
                    buffer.append(currentChar);
                    i++;
                    currentChar = expression.charAt(i);
                }

                tokens.add(buffer.toString());
                i--;

            } else if (currentChar == '-'){

                 // Primer bloque de validación (regla de los signos I).
                 if (Character.isDigit(nextChar)){
                    if (i > 0 && prevChar == currentChar){
                        // El numero se vuelve positivo.

                    } else if (i > 0 && prevChar == '+'){ // Menos por mas.
                        tokens.remove(tokens.size()-1);
                        tokens.add("-");

                    } else if (!Character.isDigit(prevChar)){
                        unaryMinus = '-';

                    } else{
                        tokens.add("-");
                    }

                    // Segundo bloque de validación (regla de los signos II).
                } else if ( !Character.isDigit(prevChar) && prevChar != '(' && prevChar != ')'){

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

                    // Contradicción.
                }else if (Character.isLetter(nextChar)){
                    unaryMinus = '-';
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
    * Guarda las funciones y sus multiples argumentos.
     */
    private static void countFunctionArguments(List<String> tokens){

        String function = "";
        int argCount = 0;

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
