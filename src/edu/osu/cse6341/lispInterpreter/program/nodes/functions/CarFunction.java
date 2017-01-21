package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class CarFunction extends BaseFunction {

    private Node address;

	public CarFunction(){}

	private CarFunction(Node params) throws Exception{
	    assertParametersAreNotEmpty(params);
	    assertLengthIsAsExpected(params.getLength());
	    ExpressionNode node = getListValue(((ExpressionNode)params).getAddress().evaluate());
        address = node.getAddress();
	}

    @Override
	public Node evaluate() throws Exception{
        return address;
	}

    @Override
	public BaseFunction newInstance(Node params) throws Exception{
		return new CarFunction(params);
	}

    @Override
    String getFunctionName() {
        return "CAR";
    }

    @Override
    int getExpectedLength() {
        return 2;
    }

}
