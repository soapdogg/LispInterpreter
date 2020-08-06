package edu.osu.cse6341.lispInterpreter.asserter;

import edu.osu.cse6341.lispInterpreter.determiner.InvalidNameDeterminer;
import edu.osu.cse6341.lispInterpreter.exceptions.InvalidUserDefinedNameException;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class UserDefinedFunctionNameAsserter {

    private final InvalidNameDeterminer invalidNameDeterminer;

    public void assertFunctionNameIsValid(String functionName) throws InvalidUserDefinedNameException {
        if(invalidNameDeterminer.isInvalidName(functionName))
            throw new InvalidUserDefinedNameException("Error! Invalid function name: " + functionName + "\n");
    }
}
