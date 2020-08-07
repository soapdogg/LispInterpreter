package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import edu.osu.cse6341.lispInterpreter.constants.TokenValueConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.ProcessedStateResult;
import edu.osu.cse6341.lispInterpreter.datamodels.Token;
import edu.osu.cse6341.lispInterpreter.datamodels.TokenKind;

public class CloseState implements IState{

	@Override
	public ProcessedStateResult processState(
		final String line,
		final int startingPos
	){
		Token token =  Token.newInstance(
		    TokenKind.CLOSE_TOKEN,
			TokenValueConstants.CLOSE_PARENTHESES
        );
		return ProcessedStateResult.newInstance(
		    token,
            startingPos + 1
        );
	}
}
