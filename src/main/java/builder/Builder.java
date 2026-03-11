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

package builder;

import tokenizer.Tokenizer;
import evaluator.Evaluator;
import parser.ExpressionHandler;
import parser.Parser;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

/**
 * Clase auxiliar encargada de construir y evaluar la expresión.
 *
 * @author Leandro J. Gómez.
 * @version 1.0.0
 * @since 1.0.0
 */
public class Builder extends ExpressionHandler {

    private String expression;
    private List<String> tokens;
    private List<String> posfixExpression;

    /**
     * Constructor base el cual solo obtiene la expresión y usa el valor del MathContext por defecto.
     * @param expression expresión a evaluar.
     */
    public Builder (String expression){
        this.expression = expression;
    }

    /**
     * Constructor basado en MathContext para precision variable.
     * @param expression expresión a evaluar.
     * @param mathContext contexto o precision en la cual se quiera evaluar la expresión matematica.
     */
    public Builder (String expression, MathContext mathContext){
        this.expression = expression;
        setMathContext(mathContext);
    }

    /**
     * Retorna la expresión original.
     * @return variable expression.
     */
    public String getExpression() {
        return expression;
    }

    /**
     * Retorna los tokens de la expresión.
     * @return variable tokens.
     */
    public List<String> getTokens() {
        return tokens;
    }

    /**
     * Retorna una lista con los tokens ordenados de manera posfija.
     * @return variable posfixExpression.
     */
    public List<String> getPosfixExpression() {
        return posfixExpression;
    }

    /**
     * Metodo para consultar el valor de un parametro.
     * @param parameterName nombre del parametro (x,y,z,etc.)
     * @return retorna el valor del parametro especificado.
     */
    public double getParameterValue(String parameterName){
        return variables.get(parameterName);
    }

    /**
     * Prosesa la expresión en tokens y posteriormente la parsea.
     */
    public void builExpression() {
        tokens = Tokenizer.tokenize(expression);
        posfixExpression = Parser.infixToPostfix(tokens);
    }

    /**
     * Método encargado de calcular el valor de la expresión dada.
     * @return retorna el valor de la expresión.
     */
    public BigDecimal evaluate(){
        return Evaluator.evaluateExpression(posfixExpression);
    }

    /**
     * Método encargado de insertar el valor de una variable/parametro en la expresión.
     * @param parameter nombre del parametro.
     * @param value valor del parametro.
     */
    public void setParameter(String parameter, double value){
        variables.put(parameter, value);
    }
}
