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

package functions.common;

import functions.Function;

/**
 * @author Leandro J. Gómez.
 * @version 1.1.0
 * @since 1.2.0
 */
public class Pow implements Function {
    @Override
    public String getName() {
        return "pow";
    }

    @Override
    public int getArgsCount() {
        return 2;
    }

    @Override
    public int getPrecedence() {
        return 3;
    }

    @Override
    public double execute(double... args) {
        return Math.pow(args[0], args[1]);
    }
}
