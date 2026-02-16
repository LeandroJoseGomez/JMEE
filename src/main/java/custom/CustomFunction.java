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
