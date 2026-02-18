package functions;

/**
 * @author Leandro Gómez.
 * @version 1.1.0
 * @since 1.1.0
 */
public interface Function {
    String getName(); // Nombre en minuscula.
    int getArgsCount();
    int getPrecedence();
    double execute(double... args);
}
