package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;

public class AtomNode extends Node{
	
	private String value;

	public AtomNode(){
	    value = Node.NIL;
    }

    public AtomNode(boolean value){
	    this.value = value ? Node.T : Node.NIL;
    }

    public AtomNode(int value){
        this.value = Integer.toString(value);
    }

	@Override
	public void parse(Tokenizer tokenizer) throws Exception{
		IToken token = tokenizer.getNextToken();
		value = token.toString();
	}

	@Override
	public Node evaluate(){
	    return this;
	}

	@Override
	public String getValueToString(){
		return value;
	}

    @Override
    public String getDotNotationToString() {
        return value;
    }

	@Override
	public Node newInstance(){
		return new AtomNode();
	}

	@Override
	public boolean isNumeric(){
        return value.matches("-?[1-9][0-9]*|0");
    }

    @Override
    public boolean isList() {
        return false;
    }

    @Override
    public int getLength(){
        return 1;
    }


}
