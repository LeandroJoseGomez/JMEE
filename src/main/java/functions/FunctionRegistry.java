package functions;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class FunctionRegistry {
    private static final Map<String, Function> functions = new HashMap<>();

    static {
        // ServiceLoader busca automáticamente todas las clases que implementan MathFunction
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
}
