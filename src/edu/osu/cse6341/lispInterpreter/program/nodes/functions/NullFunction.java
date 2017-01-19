package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class NullFunction extends BaseFunction {

	private boolean result;

	public NullFunction(){}

	private NullFunction(Node params) throws Exception{
        if(params == null) throw new Exception("Error! No parameters for the NULL Function");
        if(params.getLength() != 1) throw new Exception("Error! Expected length of NULL list is: 2    Actual" + params.getLength() + 1);

        Node evaluatedResult = params.evaluate();
		result = evaluatedResult.getValueToString().equals("NIL");
	}

    @Override
	public Node evaluate() throws Exception{
		return new AtomNode(result);
	}

    @Override
	public BaseFunction newInstance(Node params) throws Exception{
		return new NullFunction(params);
	}

}
