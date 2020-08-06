package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import edu.osu.cse6341.lispInterpreter.datamodels.ProcessedStateResult;

public interface IState{

	ProcessedStateResult processState(String line, int startingPos);
}
