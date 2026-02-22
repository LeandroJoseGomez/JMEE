/*
    Copyright (C) 2026  Leandro J. Gómez

    Este programa es software libre: usted puede redistribuirlo y/o modificarlo
    bajo los términos de la Licencia Pública General GNU publicada por
    la Fundación para el Software Libre, ya sea la versión 3 de la Licencia, o
    (a su elección) cualquier versión posterior.

    Este programa se distribuye con la esperanza de que sea útil,
    pero SIN NINGUNA GARANTÍA; sin incluso la garantía implícita de
    MERCANTILIDAD o APTITUD PARA UN PROPÓSITO PARTICULAR. Consulte la
    Licencia Pública General GNU para más detalles.

    Usted debería haber recibido una copia de la Licencia Pública General GNU
    junto con este programa. Si no es así, consulte <https://www.gnu.org/licenses/>.
 */

package parser;

import java.util.*;

/**
 * Clase encargada de convertir la expresión en notación infija a notación posfija haciendo uso
 * del algoritmo Shunting Yard creado por el cientifico.
 * @see <a>https://es.wikipedia.org/wiki/Algoritmo_shunting_yard</a>
 *
 * @author Leandro Gómez.
 * @version 1.1.0
 * @since 0.9.0
 */
public class Parser extends ExpressionHandler{

    /**
     * Esta implementacion del algoritmo Shunting Yard obvia cualquier signo o funcion que no este en la precedencia.
     * @param tokens Lista de tokes formateados proporcionada por el {@link tokenizer.Tokenizer}
     * @return Lista de tokens en notacion posfija.
     * @since 0.9.0
     */
    public static List<String> infixToPostfix(List<String> tokens) {
        List<String> posfixExpression = new ArrayList<>();
        Stack<String> operators = new Stack<>();

        for (String token : tokens) {
            if (isNumber(token)) {// Si es un número se agrega a la salida.
                posfixExpression.add(token);

            } else if(Character.isLetter(token.charAt(0)) && variables.containsKey(token)) {
                posfixExpression.add(String.valueOf(variables.get(token)));
            }
        else
            if (containsToken(token)) {
                /*
                 * Mientras que el token sea un operador con menor o igual precedencia que el operador en el tope de
                 * la pila se sacaran operadores de la pila hasta que el operador con el que se compare tenga menor
                 * precedencia.
                 */
                while (!operators.isEmpty() && containsToken(operators.peek())) {
                    if (getPrecedence(token) < getPrecedence(operators.peek())
                            || (getPrecedence(operators.peek()) == getPrecedence(token)
                            && isLeftAssociative(token))){ // Asosiativo a la derecha

                        posfixExpression.add(operators.pop());
                    } else {
                        break;
                    }
                }
                operators.push(token);// Añadir el operador luego de la comparación.

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
