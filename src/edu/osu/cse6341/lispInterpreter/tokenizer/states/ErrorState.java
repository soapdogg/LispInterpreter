package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import edu.osu.cse6341.lispInterpreter.datamodels.ProcessedStateResult;
import edu.osu.cse6341.lispInterpreter.tokens.Token;
import edu.osu.cse6341.lispInterpreter.tokens.TokenKind;


public class ErrorState implements IState{

	private static final boolean [] endOfToken;

	static{
	    endOfToken = new boolean[256];
	    endOfToken[' '] = true;
	    endOfToken['\t'] = true;
	    endOfToken['\r'] = true;
	    endOfToken['('] = true;
	    endOfToken[')'] =true;
	}

	@Override
	public ProcessedStateResult processState(String line, int startingPos){
		int pos = startingPos;
	    while(pos < line.length() && !endOfToken[line.charAt(pos)]) {
			++pos;
		}
		String fragment = line.substring(startingPos, pos);
		Token token = Token.newInstance(
			TokenKind.ERROR_TOKEN,
			fragment
		);
		return ProcessedStateResult.newInstance(
			token,
			-1
		);
	}
}
