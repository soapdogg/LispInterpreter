package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.program.IEvaluatable;
import edu.osu.cse6341.lispInterpreter.program.IParsable;
import edu.osu.cse6341.lispInterpreter.program.IPrettyPrintable;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;

public class StartNode implements IParsable, IEvaluatable, IPrettyPrintable{
    private Node node;
	private StartNode nextExpressionStartNode;

    public StartNode(){
    }

    @Override
    public void parse(Tokenizer tokenizer) throws Exception{
        if(tokenizer.getCurrent().getTokenKind() == TokenKind.EOF_TOKEN) return;
        node = Node.parseIntoNode(tokenizer);
		if(tokenizer.getCurrent().getTokenKind() == TokenKind.EOF_TOKEN) return;
		nextExpressionStartNode = new StartNode();
		nextExpressionStartNode.parse(tokenizer);
    }

	@Override
	public Node evaluate() throws Exception{
        boolean isAtom = !node.isList();
        boolean isNotNumeric = !node.isNumeric();
        boolean isNotT = !node.getValueToString().equals("T");
        boolean isNotNil = !node.getValueToString().equals("NIL");
        if(isAtom && isNotNumeric && isNotT && isNotNil) throw new Exception("Error! " + node.getValueToString() + " is not a valid atomic value!\n");
		node.evaluate();
		if(nextExpressionStartNode != null) nextExpressionStartNode.evaluate();
		return null;
	}

	@Override
    public String getValueToString(){
	    if (node == null) return "NIL\n";
		StringBuilder sb = new StringBuilder();
		sb.append(node.getValueToString());
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

}
