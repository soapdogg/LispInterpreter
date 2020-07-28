package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.program.IEvaluatable;
import edu.osu.cse6341.lispInterpreter.program.IParsable;
import edu.osu.cse6341.lispInterpreter.program.IPrettyPrintable;

public interface LispNode extends IParsable, IEvaluatable, IPrettyPrintable {

    LispNode evaluateLispNode(boolean areLiteralsAllowed) throws Exception;
    LispNode newLispNodeInstance();
    String getNodeValue();
    boolean isNodeList();
    boolean isNodeNumeric();
    int parameterLength();
}
