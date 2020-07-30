package edu.osu.cse6341.lispInterpreter.parser;

import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(staticName = "newInstance")
public class ParseTree {
    private final List<LispNode> rootNodes;
}
