package edu.osu.cse6341.lispInterpreter.determiner;

import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor(staticName = "newInstance")
public class InvalidNameDeterminer {

    private final Set<String> invalidNames;
    private final NumericStringDeterminer numericStringDeterminer;

    public boolean isInvalidName(
        final String value
    ) {
        return invalidNames.contains(value) || numericStringDeterminer.isStringNumeric(value);
    }

}
