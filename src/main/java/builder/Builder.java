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

import java.util.List;

/**
 *
 * @author Leandro Gómez.
 * @version 1.0.0
 * @since 1.0.0
 */
public class Builder extends ExpressionHandler {

    private String expression;
    private List<String> tokens;
    private List<String> posfixExpression;

    public Builder (String expression){
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public List<String> getPosfixExpression() {
        return posfixExpression;
    }

    public double getParameterValue(String parameterName){
        return variables.get(parameterName);
    }

    public void builExpression() {
        tokens = Tokenizer.tokenize(expression);
        posfixExpression = Parser.infixToPostfix(tokens);
    }

    public double evaluate(){
        return Evaluator.evaluateExpression(posfixExpression);
    }

    public void setParameter(String parameter, double value){
        variables.put(parameter, value);
    }


}
