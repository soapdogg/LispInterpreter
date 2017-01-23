package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class CdrFunction extends BaseFunction {

    private Node data;

	public CdrFunction(){}

	private CdrFunction(Node params) throws Exception{
	    assertLengthIsAsExpected(params.getLength());
	    ExpressionNode node = getListValue(((ExpressionNode)params).getAddress().evaluate());
	    data = node.getData();
    }

    @Override
	public Node evaluate() throws Exception{
        return data;
	}

    @Override
	public BaseFunction newInstance(Node params) throws Exception{
		return new CdrFunction(params);
	}

    @Override
    String getFunctionName() {
        return "CDR";
    }

    @Override
    int getExpectedLength() {
        return 2;
    }

}
