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

package functions;

/**
 * Interfaz que define la estructura de las funciones.
 *
 * @author Leandro J. Gómez.
 * @version 1.1.0
 * @since 1.2.0
 */
public interface Function {

    /**
     * Retorna el nombre de una función, el cual debe de ser definido en minusculas ej. "sin".
     * @return Nombre de la función.
     */
    String getName();

    /**
     * Retorna la cantidad de argumentos que acepta la función.
     * @return Dato entero que índica la cantidad de argumentos.
     */
    int getArgsCount();

    /**
     * Retorna la precedencia de una función (valor recomendado: 3).
     * @return Dato entero que índica la precedencia.
     */
    int getPrecedence();

    /**
     * Método encargado de definir la función en sí y como se evalúa.
     * @param args Argumentos de la función.
     * @return Dato entero que índica el resultado.
     */
    double execute(double... args);
}
