package edu.osu.cse6341.lispInterpreter.datamodels;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(staticName = "newInstance")
public class UserDefinedFunction {

    private final List<String> formalParameters;
    private final Node body;
    private final String functionName;
}
