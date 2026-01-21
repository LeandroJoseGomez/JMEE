package evaluator;

/**
 * Clase encargada de evaluar las funciones precargadas.
 * @author Leandro Gómez
 * @version 1.1.2
 */
public class Functions {

    public Functions(){

    }

    // Normales.
    public static double sqrt(double expression){
        return Math.sqrt(expression);
    }

    // Trigonometricas.
    public static double sin(double expression){
        return Math.sin(Math.toRadians(expression));
    }

    public static double cos(double expression){
        return Math.cos(Math.toRadians(expression));
    }

    public static double tan(double expression){
        return Math.tan(Math.toRadians(expression));
    }

    // Logaritmicas.
    public static double log(double expression){
        return Math.log10(expression);
    }

    public static double ln(double expression){
        return Math.log(expression);
    }
}
