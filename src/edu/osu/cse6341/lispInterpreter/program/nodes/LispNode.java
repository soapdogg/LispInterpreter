package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.program.IParsable;

public interface LispNode extends IParsable {

    Node evaluateLispNode(boolean areLiteralsAllowed) throws Exception;
    LispNode newLispNodeInstance();
    String getNodeValue();
    boolean isNodeList();
    boolean isNodeNumeric();
    int parameterLength();
}
