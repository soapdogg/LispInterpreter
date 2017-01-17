package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.program.Program;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;

public class StartNode extends Node{
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
	public Node evaluate(){
		expressionNode.evaluate();
		if(startNode != null) startNode.evaluate();
		return null;
	}

	@Override
    public String getValueToString(){
		StringBuilder sb = new StringBuilder();
		if (expressionNode.isList()) sb.append('(');
		sb.append(expressionNode.getValueToString());
		if(expressionNode.isList()) sb.append(')');
		sb.append('\n');
		if(startNode != null) sb.append(startNode.getValueToString());
		return sb.toString();
    }

    @Override
    public String getDotNotationToString(){
        String result = expressionNode.getDotNotationToString() + "\n";
        if(startNode != null) result += startNode.getDotNotationToString();
        return result;
    }

    @Override
    public Node newInstance(){
        return new StartNode();
    }

    @Override
    public boolean isList(){
        return expressionNode.isList();
    }

    @Override
    public boolean isNumeric(){
        return expressionNode.isNumeric();
    }

    @Override
    public boolean isLiteral(){
        return expressionNode.isLiteral();
    }

}
