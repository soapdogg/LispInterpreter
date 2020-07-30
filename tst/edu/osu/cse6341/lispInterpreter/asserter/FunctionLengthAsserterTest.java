package edu.osu.cse6341.lispInterpreter.asserter;

import edu.osu.cse6341.lispInterpreter.exceptions.WrongFunctionLengthException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FunctionLengthAsserterTest {

    private String functionName;
    private int expected;
    private int actual;

    private FunctionLengthAsserter functionLengthAsserter;

    @BeforeEach
    void setup() {
        functionName = "functionName";
        expected = 2;
        actual = 1;

        functionLengthAsserter = FunctionLengthAsserter.newInstance();
    }

    @Test
    void equalsTest() {
        Assertions.assertDoesNotThrow(
            () -> functionLengthAsserter.assertLengthIsAsExpected(
                functionName,
                expected,
                actual
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
                actual
            )
        );
    }
}
