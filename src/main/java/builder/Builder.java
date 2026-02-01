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
        posfixExpression = new Parser().infixToPostfix(tokens);
    }

    public double evaluate(){
        return new Evaluator().evaluateExpression(posfixExpression);
    }

    public void setParameter(String parameter, double value){
        variables.put(parameter, value);
    }


}
