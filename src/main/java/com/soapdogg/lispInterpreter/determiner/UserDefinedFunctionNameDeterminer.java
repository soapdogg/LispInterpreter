package com.soapdogg.lispInterpreter.determiner;

import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(staticName = "newInstance")
public class UserDefinedFunctionNameDeterminer {

    public boolean determineIfUserDefinedFunctionName(
        final List<UserDefinedFunction> userDefinedFunctions,
        final String functionName
    ) {
        return userDefinedFunctions.stream().anyMatch(
            userDefinedFunction -> userDefinedFunction.getFunctionName().equals(functionName)
        );
    }
}
