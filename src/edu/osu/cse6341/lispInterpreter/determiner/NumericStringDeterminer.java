package edu.osu.cse6341.lispInterpreter.determiner;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class NumericStringDeterminer {

    public boolean isStringNumeric(String value) {
        return value.matches("-?[1-9][0-9]*|0");
    }
}
