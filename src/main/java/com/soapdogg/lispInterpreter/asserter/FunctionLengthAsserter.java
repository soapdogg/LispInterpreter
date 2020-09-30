package com.soapdogg.lispInterpreter.asserter;

import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.determiner.FunctionLengthDeterminer;
import com.soapdogg.lispInterpreter.exceptions.WrongFunctionLengthException;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class FunctionLengthAsserter {

    private final FunctionLengthDeterminer functionLengthDeterminer;

    public void assertLengthIsAsExpected(
        final String functionName,
        final int expected,
        final Node node
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
