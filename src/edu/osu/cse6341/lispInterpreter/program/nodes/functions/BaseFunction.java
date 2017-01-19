package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public abstract class BaseFunction {

    public abstract Node evaluate() throws Exception;

    public abstract BaseFunction newInstance(Node node) throws Exception;

}
