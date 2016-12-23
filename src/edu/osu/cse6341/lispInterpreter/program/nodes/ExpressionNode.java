package edu.osu.cse6341.lispInterpreter.program.nodes;

import java.util.Map;
import java.util.HashMap;

import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.*;

public class ExpressionNode implements IExpressionNode{
	
	private static final Map<IToken, IExpressionChild> tokenExpressionChildMap;
	private IExpressionChild expressionChild;

    /**
     * static constructor
     */
    static
    {
        tokenExpressionChildMap = new HashMap<>();
        tokenExpressionChildMap.put(new NumericToken(), new AtomNode());
        tokenExpressionChildMap.put(new LiteralToken(), new AtomNode());
        tokenExpressionChildMap.put(new OpenToken(), new ListNode());
    }

	@Override 
	public void parse(){
		//TODO Valid token type
		expressionChild = tokenExpressionChildMap.get(Tokenizer.getTokenizer().getCurrent());
		expressionChild = expressionChild.newInstance();
		expressionChild.parse();
	}

	@Override
	public String toString(){
		return expressionChild.toString();
	}
}
