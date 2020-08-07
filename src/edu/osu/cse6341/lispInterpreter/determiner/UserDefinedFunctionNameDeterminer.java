package edu.osu.cse6341.lispInterpreter.determiner;

import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(staticName = "newInstance")
public class UserDefinedFunctionNameDeterminer {

    public boolean isUserDefinedFunctionName(
        List<UserDefinedFunction> userDefinedFunctions,
        String functionName
    ) {
        return userDefinedFunctions.stream().anyMatch(
            userDefinedFunction -> userDefinedFunction.getFunctionName().equals(functionName)
        );
    }
}
