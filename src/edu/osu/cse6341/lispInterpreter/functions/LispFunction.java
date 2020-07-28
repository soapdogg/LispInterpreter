package edu.osu.cse6341.lispInterpreter.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public interface LispFunction {
    Node evaluateLispFunction(final LispNode params) throws Exception;
}
