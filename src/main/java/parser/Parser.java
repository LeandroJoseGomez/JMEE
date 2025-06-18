/**
 * Clase encargada de convertir la expresión en notación infija a notación posfija haciendo uso
 * del algoritmo Shunting Yard.
 */
package parser;

import parser.ExpressionHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Leandro Gómez.
 * @version 1.0.0
 */
public class Parser extends ExpressionHandler{

    private List<String> tokens;
    private char unaryMinus;

    /**
     * Constructor de clase.
     * @param tokens Lista que contiene los tokens que conforman la expresión matematica.
     */
    public Parser(List<String> tokens){
        this.tokens = tokens;
        System.out.println(tokens);
    }

    /**
     * Algoritmo Shunting Yard (Esta implementación obvia cualquier signo que no este en la precedencia).
     * @return Expresión matematica en notación posfija.
     * @since 1.0.0
     */
    public List<String> infixToPostfix() {
        List<String> output = new ArrayList<>();
        Stack<String> operators = new Stack<>();

        for (String token : tokens) {
            if (isNumber(token)) {// Si es un número se agrega a la salida.
                output.add(token);
            } else
            if (isToken(token)) {
                /*
                 * Mientras que el token sea un operador con menor o igual precedencia que
                 * el operador en el tope de la pila se sacaran operadores de la pila
                 * hasta que el operador con el que se compare tenga menor precedencia.
                 */
                while (!operators.isEmpty() && precedence.containsKey(operators.peek())
                        && precedence.get(token) <= precedence.get(operators.peek())) {

                    output.add(operators.pop());
                }
                operators.push(token);// Añadir el operador luego de la comparación.

                /*
                 * Si la pila de operadores esta vacia entonces, se añade el operador directamente.
                 * Si la pila no esta vacia y el operador recibido tiene mayor precedencia que
                 * el operador del tope de la pila entonces, se añade.
                 */
                if (operators.isEmpty()) {
                    operators.push(token);
                } else
                if (precedence.get(token) > precedence.get(operators.peek())) {
                    operators.push(token);
                }

                /*
                 * Si es un paréntesis de apertura entonces, se añade directamente a la pila.
                 * Si es un paréntesis de cierre entonces, se vacia la pila hasta encontrar un
                 * paréntesis de apertura.
                 */
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {

                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    output.add(operators.pop());
                }
                operators.pop();// Se borra el paréntesis de apertura luego de comparar.
            }

        }// fin foreach.

        /*
         * Si no quedan datos para leer y la pila de operadores no esta vacia entornces,
         * los operadores en ella se vacian en la lista de salida.
         */
        while (!operators.isEmpty()) {
            output.add(operators.pop());
        }

        // Expresión en notación posfija.
        return output;
    }
}
