package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class EqFunction extends BaseFunction {

	private int length;
	private Node leftSide, rightSide;

	public EqFunction(){}

	private EqFunction(Node params){
		length = params.getLength();
		leftSide = params;
        rightSide = ((ListNode)leftSide).getData();
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
	public BaseFunction newInstance(Node listNode){
		return new EqFunction(listNode);
	}

}
