package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.program.Program;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;

public class StartNode extends Node{
    private Node node;
	private StartNode nextExpressionStartNode;

    public StartNode(){
    }

    @Override
    public void parse(Tokenizer tokenizer, Program program) throws Exception{
        if(tokenizer.getCurrent().getTokenKind() == TokenKind.EOF_TOKEN) return;
        node = Node.parseIntoNode(tokenizer, program);
		if(tokenizer.getCurrent().getTokenKind() == TokenKind.EOF_TOKEN) return;
		nextExpressionStartNode = new StartNode();
		nextExpressionStartNode.parse(tokenizer, program);
    }

	@Override
	public Node evaluate(){
		node.evaluate();
		if(nextExpressionStartNode != null) nextExpressionStartNode.evaluate();
		return null;
	}

	@Override
    public String getValueToString(){
	    if (node == null) return "NIL\n";
		StringBuilder sb = new StringBuilder();
		if (node.isList()) sb.append('(');
		sb.append(node.getValueToString());
		if(node.isList()) sb.append(')');
		sb.append('\n');
		if(nextExpressionStartNode != null) sb.append(nextExpressionStartNode.getValueToString());
		return sb.toString();
    }

    @Override
    public String getDotNotationToString(){
        if(node == null) return "NIL\n";
        String result = node.getDotNotationToString() + "\n";
        if(nextExpressionStartNode != null) result += nextExpressionStartNode.getDotNotationToString();
        return result;
    }

    @Override
    public Node newInstance(){
        return new StartNode();
    }

    @Override
    public boolean isList(){
        return node.isList();
    }

    @Override
    public boolean isNumeric(){
        return node.isNumeric();
    }

    @Override
    public boolean isLiteral(){
        return node.isLiteral();
    }

    @Override
    public int getLength(){
        return node.getLength();
    }
}
