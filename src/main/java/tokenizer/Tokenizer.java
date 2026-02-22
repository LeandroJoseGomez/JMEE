/*
    Copyright (C) 2026  Leandro J. Gómez

    Este programa es software libre: usted puede redistribuirlo y/o modificarlo
    bajo los términos de la Licencia Pública General GNU publicada por
    la Fundación para el Software Libre, ya sea la versión 3 de la Licencia, o
    (a su elección) cualquier versión posterior.

    Este programa se distribuye con la esperanza de que sea útil,
    pero SIN NINGUNA GARANTÍA; sin incluso la garantía implícita de
    MERCANTILIDAD o APTITUD PARA UN PROPÓSITO PARTICULAR. Consulte la
    Licencia Pública General GNU para más detalles.

    Usted debería haber recibido una copia de la Licencia Pública General GNU
    junto con este programa. Si no es así, consulte <https://www.gnu.org/licenses/>.
 */

package tokenizer;

import parser.ExpressionHandler;

import java.util.ArrayList;
import java.util.List;


/**
 * Clase encargada del la tokenización de la expresión original, utilizando un enfoque basado buffers para identificar
 * funciones y numeros reales.
 *
 * @author Leandro Gómez.
 * @version 1.0.0
 * @since 0.9.0
 */
public class Tokenizer extends ExpressionHandler {

    /**
     * Convierte una cadena de texto en una lista de tokens individuales.
     * Analiza caracteres de forma secuencial para distinguir entre operadores,
     * paréntesis, identificadores y números (incluyendo lógica de signos unarios).
     *
     * @param input La expresión matemática en formato String (ej. "3+sin(x)").
     * @return Una List de Strings con todos los tokes.
     * @since 0.9.0
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
            // Manejo del signo menos (Operador y Unario)
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
