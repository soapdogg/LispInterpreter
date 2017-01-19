package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class CarFunction extends BaseFunction {

    private Node address;

	public CarFunction(){}

	private CarFunction(Node params){
        address = ((ExpressionNode)params).getAddress();
	}

    @Override
	public Node evaluate() throws Exception{
        return ((ExpressionNode)address.evaluate()).getAddress();
	}

    @Override
	public BaseFunction newInstance(Node params){
		return new CarFunction(params);
	}

}
