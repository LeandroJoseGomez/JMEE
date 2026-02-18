package operators;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author Leandro Gómez.
 * @version 1.1.0
 * @since 1.1.0
 */
public class OperatorRegistry {
    private static final Map<String, Operator> operators = new HashMap<>();

    static {
        // ServiceLoader busca automáticamente todas las clases que implementan Operator.
        ServiceLoader<Operator> loader = ServiceLoader.load(Operator.class);
        for (Operator op : loader) {
            operators.put(op.getSymbol().toLowerCase(), op);
        }
    }

    public static Operator get(String name) {
        return operators.get(name.toLowerCase());
    }

    public static boolean isOperator(String name) {
        return operators.containsKey(name.toLowerCase());
    }
}
