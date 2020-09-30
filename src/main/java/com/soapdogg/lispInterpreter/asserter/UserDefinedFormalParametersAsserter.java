package com.soapdogg.lispInterpreter.asserter;

import com.soapdogg.lispInterpreter.exceptions.InvalidUserDefinedNameException;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor(staticName = "newInstance")
public class UserDefinedFormalParametersAsserter {

    private final Set<String> invalidNamesSet;

    public void assertFormalParameters(
        final List<String> formalParameters
    ) throws InvalidUserDefinedNameException {
        Set<String> formalParametersSet = new HashSet<>(formalParameters);
        if (formalParameters.size() != formalParametersSet.size()) {
            throw new InvalidUserDefinedNameException("Error! Duplicate formal parameter name!\n");
        }
        formalParametersSet.retainAll(invalidNamesSet);
        if (!formalParametersSet.isEmpty()) {
            throw new InvalidUserDefinedNameException("Error! Invalid formal parameter name!\n");
        }
    }
}
