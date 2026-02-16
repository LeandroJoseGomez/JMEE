package functions;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author Leandro Gómez.
 * @version 1.1.0
 * @since 1.1.0
 */
public class FunctionRegistry {
    private static Map<String, Function> functions = new HashMap<>();

    static {
        // ServiceLoader busca automáticamente todas las clases que implementan Function.
        ServiceLoader<Function> loader = ServiceLoader.load(Function.class);
        for (Function func : loader) {
            functions.put(func.getName().toLowerCase(), func);
        }
    }

    public static Function get(String name) {
        return functions.get(name.toLowerCase());
    }

    public static boolean isFunction(String name) {
        return functions.containsKey(name.toLowerCase());
    }

    public static void register(Function function) {
        functions.put(function.getName().toLowerCase(), function);
    }
}
