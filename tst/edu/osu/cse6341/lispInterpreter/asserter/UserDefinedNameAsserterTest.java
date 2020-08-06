package edu.osu.cse6341.lispInterpreter.asserter;

import edu.osu.cse6341.lispInterpreter.determiner.InvalidNameDeterminer;
import edu.osu.cse6341.lispInterpreter.exceptions.InvalidUserDefinedNameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UserDefinedNameAsserterTest {

    private String functionName;

    private InvalidNameDeterminer invalidNameDeterminer;

    private UserDefinedFunctionNameAsserter userDefinedFunctionNameAsserter;

    @BeforeEach
    void setup() {
        functionName = "functionName";

        invalidNameDeterminer = Mockito.mock(InvalidNameDeterminer.class);

        userDefinedFunctionNameAsserter = UserDefinedFunctionNameAsserter.newInstance(
            invalidNameDeterminer
        );
    }

    @Test
    void invalidNameTest() {
        Mockito.when(invalidNameDeterminer.isInvalidName(functionName)).thenReturn(true);

        Assertions.assertThrows(
            InvalidUserDefinedNameException.class,
            () -> userDefinedFunctionNameAsserter.assertFunctionNameIsValid(functionName)
        );
    }

    @Test
    void validNameTest() {
        Mockito.when(invalidNameDeterminer.isInvalidName(functionName)).thenReturn(false);

        Assertions.assertDoesNotThrow(
            () -> userDefinedFunctionNameAsserter.assertFunctionNameIsValid(functionName)
        );
    }
}
