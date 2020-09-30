package com.soapdogg.lispInterpreter.asserter;

import com.soapdogg.lispInterpreter.determiner.InvalidNameDeterminer;
import com.soapdogg.lispInterpreter.exceptions.InvalidUserDefinedNameException;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class UserDefinedFunctionNameAsserter {

    private final InvalidNameDeterminer invalidNameDeterminer;

    public void assertFunctionNameIsValid(
        final String functionName
    ) throws InvalidUserDefinedNameException {
        if(invalidNameDeterminer.isInvalidName(functionName))
            throw new InvalidUserDefinedNameException("Error! Invalid function name: " + functionName + "\n");
    }
}
