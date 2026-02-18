package operators;

/**
 * @author Leandro Gómez.
 * @version 1.1.0
 * @since 1.1.0
 */
public interface Operator {
    String getSymbol();
    int getPrecedence();
    int getArgsCount(); // 1 para unarios, 2 para binarios
    boolean isLeftAssociative();
    double execute(double... args);
}
