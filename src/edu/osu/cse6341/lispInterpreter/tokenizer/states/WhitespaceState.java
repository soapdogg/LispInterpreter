package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import edu.osu.cse6341.lispInterpreter.datamodels.ProcessedStateResult;

public class WhitespaceState implements IState{

	@Override
	public ProcessedStateResult processState(
		final String line,
		final int startingPos
	) {
	    return ProcessedStateResult.newInstance(
	    	null,
			startingPos + 1
		);
	}
}
