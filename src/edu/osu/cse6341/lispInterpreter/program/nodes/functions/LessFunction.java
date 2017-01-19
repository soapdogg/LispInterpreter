package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class LessFunction extends BaseFunction {

	private int length;
	private Node leftSide, rightSide;

	public LessFunction(){}

	private LessFunction(Node params){
		length = params.getLength();
		leftSide = params;
        rightSide = ((ExpressionNode)leftSide).getData();
	}

    @Override
    public Node evaluate(){
        return new AtomNode(Integer.parseInt(leftSide.evaluate().getValueToString())
                < Integer.parseInt(rightSide.evaluate().getValueToString()));
    }

    @Override
	public BaseFunction newInstance(Node params){
		return new LessFunction(params);
	}

}
