package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;

public class EqFunction implements IFunction{

	private int length;
	private ListNode leftSide, rightSide;
    private String value;

	public EqFunction(){}

	private EqFunction(ListNode listNode){
		length = listNode.getLength();
		leftSide = listNode.getListNode();
		if(leftSide != null) rightSide = leftSide.getListNode();
	}

	@Override
	public boolean isDefinedCorrectly(){
		leftSide.evaluate();
		rightSide.evaluate();
		return length == 3;
			//&& leftSide.getExpressionKind() != ExpressionKind.UNDEFINED_EXPRESSION
			//&& rightSide.getExpressionKind() != ExpressionKind.UNDEFINED_EXPRESSION;
	}  

	@Override
	public void evaluate(){
		value = leftSide.getValue().equals(rightSide.getValue())
			? "T"
			: "NIL";
	}
    @Override
    public String getValue() {
        return value;
    }
	@Override
	public IFunction newInstance(ListNode listNode){
		return new EqFunction(listNode);
	}

}
