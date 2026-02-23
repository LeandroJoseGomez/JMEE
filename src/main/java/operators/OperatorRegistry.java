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

package operators;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Esta clase se encarga de llevar un registro de todos los operadores precargados/existentes y
 * aquellos que cree el usuario.
 *
 * @author Leandro J. Gómez.
 * @version 1.1.0
 * @since 1.2.0
 */
public class OperatorRegistry {
    private static final Map<String, Operator> operators = new HashMap<>();

    static {
        // ServiceLoader busca automáticamente todas las clases que implementan Operator.
        ServiceLoader<Operator> loader = ServiceLoader.load(Operator.class);
        for (Operator op : loader) {
            operators.put(op.getSymbol().toLowerCase(), op);
        }
    }

    /**
     * Proporciona la instancia de un operador.
     * @param symbol Símbolo del operador.
     * @return Devuelve el operador especificado como una instancia de {@link operators.Operator}.
     */
    public static Operator get(String symbol) {
        return operators.get(symbol.toLowerCase());
    }

    /**
     * Verífica si el token especificado coincide con algún símbolo de operadores existentes.
     * @param symbol Símbolo del operador.
     * @return TRUE en caso de que si lo encuentre, FALSE en caso de que no.
     */
    public static boolean isOperator(String symbol) {
        return operators.containsKey(symbol.toLowerCase());
    }
}
