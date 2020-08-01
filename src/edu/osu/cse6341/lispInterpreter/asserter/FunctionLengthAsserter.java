package edu.osu.cse6341.lispInterpreter.asserter;

import edu.osu.cse6341.lispInterpreter.determiner.FunctionLengthDeterminer;
import edu.osu.cse6341.lispInterpreter.exceptions.WrongFunctionLengthException;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class FunctionLengthAsserter {

    private final FunctionLengthDeterminer functionLengthDeterminer;

    public void assertLengthIsAsExpected(
        final String functionName,
        final int expected,
        final LispNode node
    ) throws WrongFunctionLengthException {
        int actual = functionLengthDeterminer.determineFunctionLength(node);
        if(actual != expected - 1){
            String sb = "Error! Expected length of " + functionName +
                    " list is " +
                    expected +
                    "!    Actual: " +
                (actual + 1)+
                    '\n';
            throw new WrongFunctionLengthException(sb);
        }
    }
}
