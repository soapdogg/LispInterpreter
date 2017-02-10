package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class ConsFunction extends BaseFunction {

	public ConsFunction(){}

	private ConsFunction(Node params){
	    super(params);
    }

    @Override
	public Node evaluate() throws Exception{
        assertLengthIsAsExpected(params.getLength());
        ExpressionNode rightSide = (ExpressionNode) ((ExpressionNode)params).getData();
        return new ExpressionNode(((ExpressionNode) params).getAddress().evaluate(false), rightSide.getAddress().evaluate(
                false));
	}

    @Override
	public BaseFunction newInstance(Node params){
		return new ConsFunction(params);
	}

    @Override
    String getFunctionName() {
        return "CONS";
    }

    @Override
    int getExpectedLength() {
        return 3;
    }

}
