package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class EqFunction extends BaseFunction {

	private int length;
	private ListNode leftSide, rightSide;

	public EqFunction(){}

	private EqFunction(ListNode params){
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
		return new AtomNode(leftSide.evaluate().getValueToString().equals(rightSide.evaluate().getValueToString()));
	}

    @Override
	public BaseFunction newInstance(ListNode listNode){
		return new EqFunction(listNode);
	}

}
