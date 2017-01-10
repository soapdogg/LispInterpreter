package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;

public class MinusFunction implements IFunction{

	private int length;
	private ListNode leftSide, rightSide;
    private String value;

	public MinusFunction(){}

	private MinusFunction(ListNode listNode){
		length = listNode.getLength();
		leftSide = listNode.getListNode();
		if(leftSide != null) rightSide = leftSide.getListNode();
	}

	@Override
	public boolean isDefinedCorrectly(){
		leftSide.evaluate();
		rightSide.evaluate();
		return length == 3;
	}

	@Override
	public void evaluate(){
		int left = Integer.parseInt(leftSide.getValue());
		int right = Integer.parseInt(rightSide.getValue());
		value =  Integer.toString(left - right);
	}
    @Override
    public String getValue() {
        return value;
    }
	@Override
	public IFunction newInstance(ListNode listNode){
		return new MinusFunction(listNode);
	}

}
