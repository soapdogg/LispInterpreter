package edu.osu.cse6341.lispInterpreter.tokenizer.tokens;

import edu.osu.cse6341.lispInterpreter.Interpreter;

public class LiteralToken implements IToken{

	private String atomValue;

	public LiteralToken(String atomValue){
		this.atomValue = atomValue;
	}

    @Override
    public void process(Interpreter interpreter) {
        interpreter.addToLiteralAtoms(atomValue);
    }

    public TokenKind getTokenKind(){
		return TokenKind.LITERAL_TOKEN;
	}
	
	@Override
	public int hashCode(){
		return getTokenKind().ordinal();
	}
	
	@Override
	public String toString(){
		return atomValue;
	}
}
