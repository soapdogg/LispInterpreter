package edu.osu.cse6341.lispInterpreter.program.nodes;

public interface LispNode  {

    LispNode newLispNodeInstance();
    String getNodeValue();
    boolean isNodeList();
    boolean isNodeNumeric();
    int parameterLength();
}
