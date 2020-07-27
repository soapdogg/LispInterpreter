package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public interface LispFunction {
    Node evaluateLispFunction(Node params) throws Exception;
    LispFunction newFunctionInstance();
    int expectedParameterLength();
}
