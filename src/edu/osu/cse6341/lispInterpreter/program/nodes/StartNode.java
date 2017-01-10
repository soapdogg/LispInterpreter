package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.program.IEvaluatable;
import edu.osu.cse6341.lispInterpreter.program.IParsable;
import edu.osu.cse6341.lispInterpreter.program.IPrettyPrintable;
import edu.osu.cse6341.lispInterpreter.program.Program;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.EndOfFileToken;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;

public class StartNode implements IParsable, IEvaluatable, IPrettyPrintable{
    private final ExpressionNode expressionNode;
	private StartNode startNode;

    public StartNode(){
        expressionNode = new ExpressionNode();
    }

    @Override
    public void parse(Tokenizer tokenizer, Program program){
        if(tokenizer.getCurrent().getTokenKind() == TokenKind.EOF_TOKEN) return;
        expressionNode.parse(tokenizer, program);
		if(tokenizer.getCurrent().getTokenKind() == TokenKind.EOF_TOKEN
                || program.hasError()) return;
		startNode = new StartNode();
		startNode.parse(tokenizer, program);
    }

	@Override
	public void evaluate(){
		expressionNode.evaluate();
		if(startNode != null) startNode.evaluate();
	}

	@Override
    public String getValue(){
		StringBuilder sb = new StringBuilder();
		sb.append(expressionNode.getValue());
		sb.append('\n');
		if(startNode != null) sb.append(startNode.getValue());
		return sb.toString();
    }

    @Override
    public String getDotNotation()
    {
        String result = expressionNode.getDotNotation() + "\n";
        if(startNode != null) result += startNode.getDotNotation();
        return result;
    }
}
