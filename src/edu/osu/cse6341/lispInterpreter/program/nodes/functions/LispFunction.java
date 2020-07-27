package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public interface LispFunction {
    Node evaluateLispFunction() throws Exception;
    LispFunction newFunctionInstance(Node node);
    String getLispFunctionName();
    int expectedParameterLength();
}
