package com.soapdogg.lispInterpreter.asserter;

import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.determiner.FunctionLengthDeterminer;
import com.soapdogg.lispInterpreter.exceptions.WrongFunctionLengthException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class FunctionLengthAsserterTest {

    private String functionName;
    private int expected;
    private Node params;
    private int actual;

    private FunctionLengthDeterminer functionLengthDeterminer;

    private FunctionLengthAsserter functionLengthAsserter;

    @BeforeEach
    void setup() {
        functionName = "functionName";
        expected = 2;
        params = Mockito.mock(Node.class);
        actual = 1;

        functionLengthDeterminer = Mockito.mock(FunctionLengthDeterminer.class);
        Mockito.when(functionLengthDeterminer.determineFunctionLength(params)).thenReturn(actual);

        functionLengthAsserter = FunctionLengthAsserter.newInstance(
            functionLengthDeterminer
        );
    }

    @Test
    void equalsTest() {
        Assertions.assertDoesNotThrow(
            () -> functionLengthAsserter.assertLengthIsAsExpected(
                functionName,
                expected,
                params
            )
        );
    }

    @Test
    void doesNotEqualTest() {
        Assertions.assertThrows(
            WrongFunctionLengthException.class,
            () -> functionLengthAsserter.assertLengthIsAsExpected(
                functionName,
                expected + 1,
                params
            )
        );
    }
}
