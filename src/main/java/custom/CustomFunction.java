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

package custom;

import functions.Function;

import static functions.FunctionRegistry.register;

/**
 * Representa una función personalizada definida por el usuario.
 *
 * @author Leandro Gómez.
 * @version 1.0.2
 * @since 1.0.0
 */
public abstract class CustomFunction implements Function {

    private String functionName;
    private int functionArgument;
    private int precedence;

    public CustomFunction(String name, int arguments){
        functionName = name;
        functionArgument = arguments;
        precedence = 3;
    }

    @Override
    public String getName() {
        return functionName;
    }

    @Override
    public int getArgsCount() {
        return functionArgument;
    }

    @Override
    public int getPrecedence() {
        return precedence;
    }

    @Override
    public double execute(double... args) {
        return function(args);
    }

    // Método original para el usuario.
    public abstract double function(double... arguments);

    // Registra esta función en el sistema global.
    public void addFunction() {
        register(this);
    }
}
