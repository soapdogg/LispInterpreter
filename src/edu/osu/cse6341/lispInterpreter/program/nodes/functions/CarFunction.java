package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class CarFunction extends BaseFunction {

	public CarFunction(){}

	private CarFunction(Node params){
	    super(params);
	}

    @Override
	public Node evaluate() throws Exception{
        assertLengthIsAsExpected(params.getLength());
        ExpressionNode node = getListValue(((ExpressionNode)params).getAddress().evaluate(false));
        return node.getAddress();
	}

    @Override
	public BaseFunction newInstance(Node params){
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
