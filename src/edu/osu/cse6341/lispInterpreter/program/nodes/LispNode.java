package edu.osu.cse6341.lispInterpreter.program.nodes;

public interface LispNode  {

    String getNodeValue();
    boolean isNodeList();
    boolean isNodeNumeric();
    int parameterLength();
}
