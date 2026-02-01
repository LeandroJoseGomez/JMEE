package parser;

/**
 * Representa una función personalizada definida por el usuario.
 *
 * @author Leandro Gómez.
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class CustomFunction extends ExpressionHandler{

    private String functionName;
    private int functionArgument;

    public CustomFunction(String name, int arguments){
        functionName = name;
        functionArgument = arguments;
        precedence.put(functionName, 4);
    }

    public abstract double function(double... arguments);

    public int getFunctionArgument(){
        return functionArgument;
    }

    public void addFunction(){
        customFunctions.put(functionName, this);
    }
}
