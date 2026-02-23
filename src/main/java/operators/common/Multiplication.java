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

package operators.common;

import operators.Operator;

/**
 * @author Leandro J. Gómez.
 * @version 1.1.0
 * @since 1.2.0
 */
public class Multiplication implements Operator {
    @Override
    public String getSymbol() {
        return "*";
    }

    @Override
    public int getPrecedence() {
        return 1;
    }

    @Override
    public int getArgsCount() {
        return 2;
    }

    @Override
    public boolean isLeftAssociative() {
        return true;
    }

    @Override
    public double execute(double... args) {
        return args[0] * args[1];
    }
}
