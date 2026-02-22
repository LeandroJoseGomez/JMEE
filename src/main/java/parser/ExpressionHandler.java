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
 * Clase padre la cual gestiona las precedencias de los operadores y funciones al igual que los metodos de verificación.
 *
 * @author Leandro Gómez.
 * @version 1.1.0
 * @since 0.9.0
 */
public class ExpressionHandler {

    protected static HashMap<String, Double> variables = new HashMap<>();

    protected static int getPrecedence(String token) {
        if (OperatorRegistry.isOperator(token)) {
            return OperatorRegistry.get(token).getPrecedence();
        }
        if (FunctionRegistry.isFunction(token)) {
            return FunctionRegistry.get(token).getPrecedence();
        }
        return 0; // Por defecto
    }

    protected static boolean containsToken(String token){
        return OperatorRegistry.isOperator(token) || FunctionRegistry.isFunction(token);
    }

    protected static boolean isLeftAssociative(String token) {
        if (OperatorRegistry.isOperator(token)) {
            return OperatorRegistry.get(token).isLeftAssociative();
        }
        return false;
    }

    /**
     * Verifica si el token en cuestión es un número.
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
