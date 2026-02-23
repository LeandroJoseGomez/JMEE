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
 * @author Leandro J. Gómez.
 * @version 1.0.2
 * @since 1.0.0
 */
public abstract class CustomFunction implements Function {

    private String functionName;
    private int functionArgument;
    private int precedence;

    /**
     * Constructor de clase que obtiene el nombre y
     * cantidad de argumentos de la función.
     *
     * @param name nombre de la función.
     * @param arguments número de argumentos que tendra la función.
     */
    public CustomFunction(String name, int arguments){
        functionName = name;
        functionArgument = arguments;
        precedence = 3;
    }

    /**
     * Retorna el nombre de la función.
     * @return variable functionName.
     */
    @Override
    public String getName() {
        return functionName;
    }

    /**
     * Retorna la cantidad de argumentos.
     * @return variable functionArgument.
     */
    @Override
    public int getArgsCount() {
        return functionArgument;
    }

    /**
     * Retorna la precedencia de la función.
     * @return variable precedence.
     */
    @Override
    public int getPrecedence() {
        return precedence;
    }

    /**
     * Metodo de la interfaz {@link functions.Function} para evaluar la función.
     * @param args argumentos de la función.
     * @return resultado de la función.
     */
    @Override
    public double execute(double... args) {
        return function(args);
    }

    /**
     * Método propio para definir la función.
     * @param args argumentos de la función.
     * @return resultado de la función.
     */
    public abstract double function(double... args);

    // Registra esta función en el sistema global.

    /**
     * Encargado de añadir la función al registro global.
     */
    public void addFunction() {
        register(this);
    }
}
