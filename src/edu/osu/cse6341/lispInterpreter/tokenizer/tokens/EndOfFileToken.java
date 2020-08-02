package edu.osu.cse6341.lispInterpreter.tokenizer.tokens;

public class EndOfFileToken implements IToken{

    public TokenKind getTokenKind(){
		return TokenKind.EOF_TOKEN;
	}
	
	@Override
	public String toString(){
		return "EOF";
	}
}
