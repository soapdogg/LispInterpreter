package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.program.types.*;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;

public class AtomNode extends Node{
	
	private String value;

	public AtomNode(){
	    value = Node.NIL;
    }

    public AtomNode(boolean value){
	    this.value = value ? Node.T : Node.F;
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
    public IType typeCheck(boolean areLiteralsAllowed) throws Exception{
        if(Node.equalsNil(value)) {
            type = new ListType(0);
            return type;
        }
        if(Node.equalsT(value)){
            type = new TrueType();
            return type;
        }
        if(Node.equalsF(value)) {
            type = new FalseType();
            return type;
        }
        if(isNumeric()){
            type = new AnyNatType();
            return type;
        }
        throw new Exception("ATOM TYPE CHECK: " + value );
    }

	@Override
	public Node evaluate(boolean areLiteralsAllowed){
        return this;
	}

	@Override
	public String getListNotationToString(boolean isFirst){
		return value;
	}

    @Override
    public String getDotNotationToString() {
        return value;
    }

    @Override
    public String getTypeToString(){
	    return type.toString();
    }

	@Override
	public Node newInstance(){
		return new AtomNode();
	}

    @Override
    public String getValue(){
        return value;
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
