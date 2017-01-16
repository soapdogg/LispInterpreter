package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class MinusFunction extends BaseFunction {

	private int length;
	private ListNode leftSide, rightSide;

	public MinusFunction(){}

	private MinusFunction(ListNode params){
		length = params.getLength();
		leftSide = params;
		rightSide = leftSide.getData();
	}

	@Override
	public boolean hasError(){
		leftSide.evaluate();
		rightSide.evaluate();
		return length == 3;
	}

	@Override
	public Node evaluate(){
        return new AtomNode(
                Integer.parseInt(leftSide.evaluate().getValueToString())
                        - Integer.parseInt(rightSide.evaluate().getValueToString())
        );
	}

    @Override
	public BaseFunction newInstance(ListNode params){
		return new MinusFunction(params);
	}

}
