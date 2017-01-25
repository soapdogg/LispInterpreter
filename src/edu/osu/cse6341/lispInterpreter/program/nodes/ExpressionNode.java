package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;

public class ExpressionNode extends Node{

	private Node address;
	private Node data;
	private boolean isList;

	ExpressionNode(){
	    address = null;
	    data = null;
	    isList = false;
	}

	@Override
	public void parse(Tokenizer tokenizer) throws Exception{
		TokenKind tokenKind = tokenizer.getCurrent().getTokenKind();
	    isList = tokenKind != TokenKind.CLOSE_TOKEN;
		if(!isList) return;

		address = Node.parseIntoNode(tokenizer);

        data = new ExpressionNode();
        data.parse(tokenizer);
	}

	@Override
	public Node newInstance(){
		return new ExpressionNode();
	}

    @Override
    public String getDotNotationToString() {
        if(isList){
            StringBuilder sb = new StringBuilder();
            sb.append('(');
            sb.append(address.getDotNotationToString());
            sb.append(" . ");
            sb.append(data.getDotNotationToString());
            sb.append(')');
            return sb.toString();
        }
        return Node.NIL;
    }

}
