package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import edu.osu.cse6341.lispInterpreter.datamodels.ProcessedStateResult;
import edu.osu.cse6341.lispInterpreter.datamodels.Token;
import edu.osu.cse6341.lispInterpreter.datamodels.TokenKind;

public class NumericState implements IState{

	private static final boolean [] nextStateArray;
	private static final boolean [] errorArray;

    static {
		nextStateArray = new boolean [256];
		for(char i = '0'; i <= '9'; ++i) nextStateArray[i] = true;
		errorArray = new boolean[256];
		for(char i = 'A'; i <= 'Z'; ++i) errorArray[i] = true;
	}

	@Override
	public ProcessedStateResult processState(String line, int startingPos){
	    int pos = startingPos;

        while(pos < line.length() && (nextStateArray[line.charAt(pos)] || errorArray[line.charAt(pos)])) {
            char currentChar = line.charAt(pos);
            if(errorArray[currentChar]) {
                ErrorState errorState = new ErrorState();
                return errorState.processState(line, startingPos);
            }
            ++pos;
        }
        String fragment = line.substring(startingPos, pos);
        Token token = Token.newInstance(
            TokenKind.NUMERIC_TOKEN,
            fragment
        );

        return ProcessedStateResult.newInstance(
            token,
            pos
        );
	}
}
