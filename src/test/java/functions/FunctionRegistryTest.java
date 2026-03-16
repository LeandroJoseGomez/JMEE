package functions;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.junit.jupiter.api.Assertions.*;

class FunctionRegistryTest {

    private final String[] FUN_NAMES = {"Sin", "cOs", "taN", "Log", "lN", "sqrt"};

    @Test
    void get() {
        for(String functionName : FUN_NAMES){
            Function fun = FunctionRegistry.get(functionName);
            assertNotNull(fun, "Función no encontrada");
        }

    }

    @Test
    void isFunction() {
        for(String functionName : FUN_NAMES){
            assertTrue(FunctionRegistry.isFunction(functionName));
        }
    }

    @Test
    void register() {
        Function function = new Function() {
            @Override
            public String getName() {
                return "sum";
            }

            @Override
            public int getArgsCount() {
                return 2;
            }

            @Override
            public int getPrecedence() {
                return 3;
            }

            @Override
            public BigDecimal execute(MathContext mathContext, BigDecimal... args) {
                return args[0].add(args[1], mathContext);
            }
        };
        FunctionRegistry.register(function);

        assertTrue(FunctionRegistry.isFunction(function.getName()));
    }
}