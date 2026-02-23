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

/**
 * Interfaz que define la estructura de las funciones.
 *
 * @author Leandro J. Gómez.
 * @version 1.1.0
 * @since 1.2.0
 */
public interface Operator {
    /**
     * Retorna el símbolo de un operador.
     * @return Símbolo del operador.
     */
    String getSymbol();

    /**
     * Retorna la precedencia de un operador (valor minimo recomendado: 0).
     * @return Dato entero que índica la precedencia.
     */
    int getPrecedence();

    /**
     * Retorna la cantidad de argumentos que acepta el operador.
     * @return Dato entero que índica la cantidad de argumentos.
     */
    int getArgsCount();

    /**
     * Encargado de establecer si el operador es asociativo a la izquierda o no.
     * @return TRUE en caso de que si lo sea, FALSE en caso que no.
     */
    boolean isLeftAssociative();

    /**
     * Método encargado de definir el operador.
     * @param args Argumentos aceptados por el operador.
     * @return Dato entero que índica el resultado.
     */
    double execute(double... args);
}
