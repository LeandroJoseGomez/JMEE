/**
 * Clase encargada del formateo y tokenización de la expresión original utilizando un algoritmo propio titulado Sorting Buffer.
 */
package Tokenizer;

import parser.ExpressionHandler;

import java.util.*;

/**
 *
 * @author Leandro Gómez.
 * @version 1.1.2
 */
public class Tokenizer extends ExpressionHandler {

    /**
     * Divide y tokeniza la expresión haciendo uso del algoritmo Sorting Buffer.
     * @return Lista que contiene la expresión original ya tokenizada.
     */
    public static List<String> tokenize(String input) {
        if (input == null || input.isEmpty()) return new ArrayList<>();

        // El formateo debe devolver la cadena limpia.
        String expr = input.replaceAll("\\s+", "").toLowerCase();
        List<String> tokens = new ArrayList<>();
        int n = expr.length();

        for (int i = 0; i < n; i++) {
            char c = expr.charAt(i);

            if (Character.isWhitespace(c)) continue;

            // Buffer de Números (incluye decimales)
            if (Character.isDigit(c) || c == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < n && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    sb.append(expr.charAt(i++));
                }
                tokens.add(sb.toString());
                i--; // Ajuste de índice
            }
            // Manejo de Identificadores (Funciones o Variables)
            else if (Character.isLetter(c)) {
                StringBuilder sb = new StringBuilder();
                while (i < n && (Character.isLetter(expr.charAt(i)) || Character.isDigit(expr.charAt(i)))) {
                    sb.append(expr.charAt(i++));
                }
                tokens.add(sb.toString());
                i--;
            }
            // Manejo del signo menos (Operador vs Unario)
            else if (c == '-') {
                // Es unario si: es el inicio, o sigue a un operador/paréntesis abierto
                boolean isUnary = (i == 0);
                if (!isUnary && i > 0) {
                    String prev = tokens.get(tokens.size() - 1);
                    // Si lo anterior fue un operador o '(', este '-' es parte del número
                    isUnary = "+-*/(^".contains(prev);
                }

                if (isUnary && i + 1 < n && (Character.isDigit(expr.charAt(i + 1)) || expr.charAt(i + 1) == '.')) {
                    StringBuilder sb = new StringBuilder("-");
                    i++;
                    while (i < n && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                        sb.append(expr.charAt(i++));
                    }
                    tokens.add(sb.toString());
                    i--;
                } else {
                    tokens.add("-");
                }
            }
            //Otros operadores y paréntesis
            else {
                tokens.add(String.valueOf(c));
            }
        }
        return tokens;
    }
}
