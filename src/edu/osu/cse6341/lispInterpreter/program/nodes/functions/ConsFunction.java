package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class ConsFunction extends BaseFunction {

    private Node leftSide, rightSide;
    private int length;

	public ConsFunction(){}

	private ConsFunction(Node params){
        length = params.getLength();
        leftSide = params;
        rightSide = ((ExpressionNode)leftSide).getData();
    }

    @Override
	public Node evaluate() throws Exception{
        Node leftResult = leftSide.evaluate();
        Node rightResult = rightSide.evaluate();
        return new ExpressionNode(leftResult, rightResult);
	}

    @Override
	public BaseFunction newInstance(Node params){
		return new ConsFunction(params);
	}

}
