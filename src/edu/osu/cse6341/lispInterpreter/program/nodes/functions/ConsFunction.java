package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class ConsFunction extends BaseFunction implements LispFunction {

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

    @Override
    public Node evaluateLispFunction() throws Exception {
        return evaluate();
    }

    @Override
    public LispFunction newFunctionInstance(Node node) {
        return new ConsFunction(node);
    }

    @Override
    public String getLispFunctionName() {
        return getFunctionName();
    }

    @Override
    public int expectedParameterLength() {
        return getExpectedLength();
    }
}
