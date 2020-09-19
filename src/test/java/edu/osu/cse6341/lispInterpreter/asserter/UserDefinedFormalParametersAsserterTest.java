package edu.osu.cse6341.lispInterpreter.asserter;

import edu.osu.cse6341.lispInterpreter.asserter.UserDefinedFormalParametersAsserter;
import edu.osu.cse6341.lispInterpreter.exceptions.InvalidUserDefinedNameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

class UserDefinedFormalParametersAsserterTest {

    private List<String> formalParameters;

    private String invalidName;
    private Set<String> invalidNamesSet;

    private UserDefinedFormalParametersAsserter userDefinedFormalParametersAsserter;

    @BeforeEach
    void setup() {
        invalidName = "invalidName";
        invalidNamesSet = Set.of(invalidName);

        userDefinedFormalParametersAsserter = UserDefinedFormalParametersAsserter.newInstance(
            invalidNamesSet
        );
    }

    @Test
    void duplicateFormalParameterNameTest() {
        String duplicate = "duplicate";
        formalParameters = List.of(duplicate, duplicate);

        Assertions.assertThrows(
            InvalidUserDefinedNameException.class,
            () -> userDefinedFormalParametersAsserter.assertFormalParameters(
                formalParameters
            )
        );
    }

    @Test
    void invalidNameTest() {
        formalParameters = List.of(invalidName);

        Assertions.assertThrows(
            InvalidUserDefinedNameException.class,
            () -> userDefinedFormalParametersAsserter.assertFormalParameters(
                formalParameters
            )
        );
    }

    @Test
    void validNameTest() {
        String valid = "valid";
        formalParameters = List.of(valid);

        Assertions.assertDoesNotThrow(
            () -> userDefinedFormalParametersAsserter.assertFormalParameters(
                formalParameters
            )
        );
    }
}
