package edu.osu.cse6341.lispInterpreter.program.asserter;

import edu.osu.cse6341.lispInterpreter.exceptions.WrongFunctionLengthException;

public class FunctionLengthAsserter {

    public void assertLengthIsAsExpected(
        String functionName,
        int expected,
        int actual
    ) throws WrongFunctionLengthException {
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
