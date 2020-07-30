package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.program.IPrettyPrintable;

public interface LispNode extends IPrettyPrintable {

    String getNodeValue();
    boolean isNodeList();
    boolean isNodeNumeric();
    int parameterLength();
}
