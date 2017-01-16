package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.program.Program;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;

public class AtomNode extends Node{
	
	private String value;

	public AtomNode(){
	    value = "NIL";
    }

    public AtomNode(boolean value){
	    this.value = value ? "T" : "NIL";
    }

    public AtomNode(int value){
        this.value = Integer.toString(value);
    }

    public AtomNode(String value){
	    this.value = value;
    }

	@Override
	public void parse(Tokenizer tokenizer, Program program){
		IToken token = tokenizer.getNextToken();
		if(!assertTokenIsAtom(token, program)) return;
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

	public boolean isNumeric(){
        return value.matches("[\\d+\\-]?[\\d]");
    }

    public boolean isLiteral(){
	    return value.matches("[A-Z][A-Z0-9]*");
    }

    public boolean isList(){
        return false;
    }

	private static boolean assertTokenIsAtom(IToken token, Program program){
		TokenKind tokenKind = token.getTokenKind();
		boolean result = (tokenKind == TokenKind.NUMERIC_TOKEN ||
			tokenKind == TokenKind.LITERAL_TOKEN);
		if(!result){
		    program.markErrorPresent();
		    String errorMessage = "Expected NUMERIC or LITERAL token.\n"
                    + "Actual: " + token.getTokenKind() + "\tValue: " + token.toString();
		    program.setErrorMessage(errorMessage);
        }
        return result;
	}
}
