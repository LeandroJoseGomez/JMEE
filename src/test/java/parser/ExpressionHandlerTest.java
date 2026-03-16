package parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionHandlerTest {

    private final String[] FUN_NAMES = {"Sin", "cOs", "taN", "Log", "lN", "sqrt"};
    private final String[] OP_SYMBOLS = {"+", "-", "*", "/", "^"};

    @Test
    void getPrecedence() {
        for(String funName: FUN_NAMES){
            assertNotNull(ExpressionHandler.getPrecedence(funName));
        }

        for(String opSymbols: OP_SYMBOLS){
            assertNotNull(ExpressionHandler.getPrecedence(opSymbols));
        }
    }

    @Test
    void containsToken() {
        for(String funName: FUN_NAMES){
            assertTrue(ExpressionHandler.containsToken(funName));
        }

        for(String opSymbols: OP_SYMBOLS){
            assertTrue(ExpressionHandler.containsToken(opSymbols));
        }
    }

    @Test
    void isLeftAssociative() {
    }

    @Test
    void isNumber() {
    }
}