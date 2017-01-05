package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.program.IEvaluatable;
import edu.osu.cse6341.lispInterpreter.program.IParsable;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.EndOfFileToken;

public class StartNode implements IParsable, IEvaluatable{
    private final ExpressionNode expressionNode;
	private StartNode startNode;

    public StartNode(){
        expressionNode = new ExpressionNode();
    }

    @Override
    public void parse(){
        expressionNode.parse();
		if(Tokenizer.getTokenizer().getCurrent() instanceof EndOfFileToken) return;
		startNode = new StartNode();
		startNode.parse();
    }

	@Override
	public void evaluate(){
		expressionNode.evaluate();
		if(startNode != null) startNode.evaluate();
	}

    @Override
    public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(expressionNode.toString());
		sb.append('\n');
		if(startNode != null) sb.append(startNode.toString());
		return sb.toString();
    }
}
