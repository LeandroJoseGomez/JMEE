package functions;

public interface Function {
    String getName(); // Nombre en minuscula.
    int getArgsCount();
    int getPrecedence();
    double execute(double... args);
}
