package edu.osu.cse6341.lispInterpreter.program;

import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import lombok.AllArgsConstructor;

import java.util.Map;


@AllArgsConstructor(staticName = "newInstance")
public class Environment {
    private Map<String, LispNode> variables;


}
