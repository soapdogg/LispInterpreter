package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import edu.osu.cse6341.lispInterpreter.datamodels.ProcessedStateResult;
import edu.osu.cse6341.lispInterpreter.tokens.Token;
import edu.osu.cse6341.lispInterpreter.tokens.TokenKind;

public class OpenState implements IState{
	@Override
	public ProcessedStateResult processState(
		final String line,
		final int startingPos
	) {
		Token token = Token.newInstance(
		    TokenKind.OPEN_TOKEN,
            "("
        );
		return ProcessedStateResult.newInstance(
		 	token,
			startingPos + 1
		);
	}
}
