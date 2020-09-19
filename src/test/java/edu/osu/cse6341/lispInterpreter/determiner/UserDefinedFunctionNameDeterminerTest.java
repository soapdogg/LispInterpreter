package edu.osu.cse6341.lispInterpreter.determiner;

import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.determiner.UserDefinedFunctionNameDeterminer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

class UserDefinedFunctionNameDeterminerTest {

    private List<UserDefinedFunction> userDefinedFunctions;
    private String functionName;

    private UserDefinedFunctionNameDeterminer userDefinedFunctionNameDeterminer;

    @BeforeEach
    void setup() {
        userDefinedFunctions = Collections.emptyList();
        functionName = "functionName";

        userDefinedFunctionNameDeterminer = UserDefinedFunctionNameDeterminer.newInstance();
    }

    @Test
    void isUserDefinedFunctionTrueTest() {
        UserDefinedFunction userDefinedFunction = Mockito.mock(UserDefinedFunction.class);
        Mockito.when(userDefinedFunction.getFunctionName()).thenReturn(functionName);

        userDefinedFunctions = Collections.singletonList(userDefinedFunction);

        boolean actual = userDefinedFunctionNameDeterminer.determineIfUserDefinedFunctionName(
            userDefinedFunctions,
            functionName
        );

        Assertions.assertTrue(actual);
    }
}
