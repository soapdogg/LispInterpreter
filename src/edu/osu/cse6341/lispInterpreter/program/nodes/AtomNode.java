package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;

public class AtomNode extends Node{
	
	private String value;

	AtomNode(){
	    value = Node.NIL;
    }

	@Override
	public void parse(Tokenizer tokenizer) throws Exception{
		IToken token = tokenizer.getNextToken();
		value = token.toString();
	}

    @Override
    public String getDotNotationToString() {
        return value;
    }

	@Override
	public Node newInstance(){
		return new AtomNode();
	}
}
