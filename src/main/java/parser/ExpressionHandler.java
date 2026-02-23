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

import functions.FunctionRegistry;
import operators.OperatorRegistry;

import java.util.HashMap;

/**
 * Clase global la cual agrupa y distribuye los metodos necesarios para la verificacion de expresiones.
 *
 * @author Leandro J. Gómez.
 * @version 1.1.0
 * @since 0.9.0
 */
public class ExpressionHandler {

    /**
     * Hashmap auxiliar para guardar las variables/parametros y su respectivo valor.
     */
    protected static HashMap<String, Double> variables = new HashMap<>();

    /**
     * Identifica si se trata de un operador o función y posteriormente devuelve el dato correspondiente.
     * @param token token el cual puede ser un operador, función u operando.
     * @return Dato entero que representa la precedencia.
     */
    protected static int getPrecedence(String token) {
        if (OperatorRegistry.isOperator(token)) {
            return OperatorRegistry.get(token).getPrecedence();
        }
        if (FunctionRegistry.isFunction(token)) {
            return FunctionRegistry.get(token).getPrecedence();
        }
        return 0; // Por defecto
    }

    /**
     * Verífica si el token es una función u operador válido.
     * @param token token el cual puede ser un operador, función u operando.
     * @return TRUE en caso de que si lo sea, FALSE en caso que no.
     */
    protected static boolean containsToken(String token){
        return OperatorRegistry.isOperator(token) || FunctionRegistry.isFunction(token);
    }

    /**
     * Verífica si el operador es asociativo a la izquierda o no.
     * @return TRUE en caso de que si lo sea, FALSE en caso que no.
     */
    protected static boolean isLeftAssociative(String token) {
        if (OperatorRegistry.isOperator(token)) {
            return OperatorRegistry.get(token).isLeftAssociative();
        }
        return false;
    }

    /**
     * Verífica si el token en cuestión es un número.
     * @param token Posible signo, número o función de la expresión a evaluar.
     * @return TRUE en caso de que sea un número y FALSE en caso de que no lo sea.
     */
    protected static boolean isNumber(String token) {
        try {
            Double.valueOf(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
