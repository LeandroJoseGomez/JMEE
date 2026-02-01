
package parser;

import java.util.*;

/**
 * Clase encargada de convertir la expresión en notación infija a notación posfija haciendo uso
 * del algoritmo Shunting Yard creado por el cientifico.
 * @see <a>https://es.wikipedia.org/wiki/Algoritmo_shunting_yard</a>
 *
 * @author Leandro Gómez.
 * @version 1.0.0
 * @since 0.9.0
 */
public class Parser extends ExpressionHandler{

    /**
     * Esta implementacion del algoritmo Shunting Yard obvia cualquier signo o funcion que no este en la precedencia.
     * @param tokens Lista de tokes formateados proporcionada por el {@link tokenizer.Tokenizer}
     * @return Lista de tokens en notacion posfija.
     * @since 0.9.0
     */
    public List<String> infixToPostfix(List<String> tokens) {
        List<String> posfixExpression = new ArrayList<>();
        Stack<String> operators = new Stack<>();

        for (String token : tokens) {
            if (isNumber(token)) {// Si es un número se agrega a la salida.
                posfixExpression.add(token);

            } else if(Character.isLetter(token.charAt(0)) && variables.containsKey(token)) {
                posfixExpression.add(String.valueOf(variables.get(token)));
            }
        else
            if (isToken(token)) {
                /*
                 * Mientras que el token sea un operador con menor o igual precedencia que
                 * el operador en el tope de la pila se sacaran operadores de la pila
                 * hasta que el operador con el que se compare tenga menor precedencia.
                 */
                while (!operators.isEmpty() && precedence.containsKey(operators.peek())
                        && precedence.get(token) <= precedence.get(operators.peek())) {

                    posfixExpression.add(operators.pop());
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
                    posfixExpression.add(operators.pop());
                }
                operators.pop();// Se borra el paréntesis de apertura luego de comparar.
            }

        }// fin foreach.

        /*
         * Si no quedan datos para leer y la pila de operadores no esta vacia entornces,
         * los operadores en ella se vacian en la lista de salida.
         */
        while (!operators.isEmpty()) {
            posfixExpression.add(operators.pop());
        }

        // Expresión en notación posfija.
        return posfixExpression;
    }
}
