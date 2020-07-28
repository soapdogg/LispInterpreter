package edu.osu.cse6341.lispInterpreter.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;

public interface LispFunction {
    LispNode evaluateLispFunction(final LispNode params) throws Exception;
}
