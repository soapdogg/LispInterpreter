package edu.osu.cse6341.lispInterpreter.tokenizer.tokens;

import edu.osu.cse6341.lispInterpreter.Interpreter;

public class ErrorToken implements IToken{

	private final String atomValue;

	public ErrorToken(String atomValue){
		this.atomValue = atomValue;
	}

    @Override
    public void process(Interpreter interpreter) {
        interpreter.setErrorMessage("Error! Invalid token: " + atomValue + "\n");
    }

    public TokenKind getTokenKind(){
		return TokenKind.ERROR_TOKEN;
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
