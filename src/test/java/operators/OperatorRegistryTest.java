package operators;

import functions.Function;
import functions.FunctionRegistry;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.junit.jupiter.api.Assertions.*;

class OperatorRegistryTest {

    private final String[] OP_SYMBOLS = {"+", "-", "*", "/", "^"};


    @Test
    void get() {
        for(String operatorSymbol : OP_SYMBOLS){
            Operator op = OperatorRegistry.get(operatorSymbol);
            assertNotNull(op, "Operador no encontrado");
        }
    }

    @Test
    void isOperator() {
        for(String operatorSymbol : OP_SYMBOLS){
            assertTrue(OperatorRegistry.isOperator(operatorSymbol));
        }
    }

    @Test
    void register() {
         Operator operator = new Operator() {
            @Override
            public String getSymbol() {
                return "%";
            }

            @Override
            public int getPrecedence() {
                return 2;
            }

            @Override
            public int getArgsCount() {
                return 2;
            }

            @Override
            public boolean isLeftAssociative() {
                return true;
            }

            @Override
            public BigDecimal execute(MathContext mathContext, BigDecimal... args) {
                return (args[0].divide(args[1], mathContext)).pow(2);
            }
        };

        OperatorRegistry.register(operator);

        assertTrue(OperatorRegistry.isOperator(operator.getSymbol()));
    }
}