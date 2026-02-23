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

package evaluator;

import functions.Function;
import functions.FunctionRegistry;
import operators.Operator;
import operators.OperatorRegistry;
import parser.ExpressionHandler;

import java.util.List;
import java.util.Stack;

/**
 * Clase encargada de analizar y evaluar la expresión dada en notación posfija, siguiendo la implementación del
 * algoritmo Shunting Yard
 *
 * @author Leandro J. Gómez.
 * @version 1.1.0
 * @since 0.9.0
 */
public class Evaluator extends ExpressionHandler{

    /**
     * Metodo encargado de evaluar la expresión.
     * @param posfixExpression Lista de String que contiene los tokens de la expresión en notación posfija.
     * @return Variable double con el resultado de la expresión evaluada en notación posfija.
     */
    public static double evaluateExpression(List<String> posfixExpression) {
        if (posfixExpression == null) {
            throw new IllegalStateException("Se debe llamar al metodo parseExpression() antes de evaluar.");
        }

        Stack<Double> stack = new Stack<>();

        for (String token : posfixExpression) {
            if (isNumber(token)) {
                stack.push(Double.valueOf(token));
            } else if (OperatorRegistry.isOperator(token)) {

                Operator op = OperatorRegistry.get(token);
                final int numArgs = op.getArgsCount();

                final double[] args = new double[numArgs];
                for (int i = numArgs - 1; i >= 0; i--) { //va e atras en adelante rellenando.
                    if (stack.isEmpty()) throw new IllegalArgumentException("Faltan argumentos para: " + token);
                    args[i] = stack.pop();
                }

                // Se ejecuta la clase correspondiente (Addition, Subtraction, etc.)
                stack.push(op.execute(args));

            } else if (FunctionRegistry.isFunction(token)) {

                Function func = FunctionRegistry.get(token);
                final int numArgs = func.getArgsCount();

                final double[] args = new double[numArgs];
                for (int i = numArgs - 1; i >= 0; i--) {
                    if (stack.isEmpty()) throw new IllegalArgumentException("Faltan argumentos para: " + token);
                    args[i] = stack.pop();
                }

                // Se ejecuta la clase correspondiente (Sin, Mean, Sqrt, etc.)
                stack.push(func.execute(args));

            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("La expresión es inválida: sobran operandos.");
        }


        return stack.pop(); // Resultado de la operacion.
    }
}
