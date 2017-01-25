package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.program.IParsable;
import edu.osu.cse6341.lispInterpreter.program.IPrettyPrintable;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;

public class StartNode implements IParsable, IPrettyPrintable{
    private Node node;
	private StartNode nextExpressionStartNode;


    @Override
    public void parse(Tokenizer tokenizer) throws Exception{
        if(tokenizer.getCurrent().getTokenKind() == TokenKind.EOF_TOKEN) return;
        node = Node.parseIntoNode(tokenizer);
		if(tokenizer.getCurrent().getTokenKind() == TokenKind.EOF_TOKEN) return;
		nextExpressionStartNode = new StartNode();
		nextExpressionStartNode.parse(tokenizer);
    }

    @Override
    public String getDotNotationToString(){
        if(node == null) return Node.NIL + "\n";
        StringBuilder result = new StringBuilder(node.getDotNotationToString());
        result.append('\n');
        if(nextExpressionStartNode != null) result.append(nextExpressionStartNode.getDotNotationToString());
        return result.toString();
    }
}
