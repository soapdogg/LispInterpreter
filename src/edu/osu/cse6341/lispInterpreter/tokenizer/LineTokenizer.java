package edu.osu.cse6341.lispInterpreter.tokenizer;

import edu.osu.cse6341.lispInterpreter.datamodels.ProcessedStateResult;
import edu.osu.cse6341.lispInterpreter.datamodels.Token;
import edu.osu.cse6341.lispInterpreter.tokenizer.states.*;
import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.Queue;

@AllArgsConstructor(staticName = "newInstance")
public class LineTokenizer {

    private static final IState [] nextStateArray;

    static{
        nextStateArray = new IState[256];
        for(char i = 'A'; i <= 'Z'; ++i) nextStateArray[i] = new LiteralState();
        for(char i = '0'; i <= '9'; ++i) nextStateArray[i] = new NumericState();
        nextStateArray['\t'] = new WhitespaceState();
        nextStateArray[' '] = new WhitespaceState();
        nextStateArray['\r'] = new WhitespaceState();
        nextStateArray['('] = new OpenState();
        nextStateArray[')'] = new CloseState();
    }

    public Queue<Token> tokenizeLine(
        String line
    ) {
        int startingPos = 0;
        Queue<Token> tokens = new LinkedList<>();

        while (startingPos < line.length()) {
            IState state = nextStateArray[line.charAt(startingPos)];
            ProcessedStateResult processedStateResult = state.processState(
                line,
                startingPos
            );
            Token token = processedStateResult.getToken();
            startingPos = processedStateResult.getStartingPos();
            if (token != null) tokens.add(token);
        }
        return tokens;
    }
}
