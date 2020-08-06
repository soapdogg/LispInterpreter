package edu.osu.cse6341.lispInterpreter.datamodels;

import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(staticName = "newInstance")
public class UserDefinedFunction {

    private final List<String> formalParameters;
    private final LispNode body;
    private final String functionName;
}
