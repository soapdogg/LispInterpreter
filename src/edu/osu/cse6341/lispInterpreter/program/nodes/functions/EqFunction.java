package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;
import edu.osu.cse6341.lispInterpreter.program.ExpressionKind;

public class EqFunction implements IFunction{

	private int length;
	private ListNode leftSide, rightSide;

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
		return length == 3 
			&& leftSide.getExpressionKind() != ExpressionKind.UNDEFINED_EXPRESSION
			&& rightSide.getExpressionKind() != ExpressionKind.UNDEFINED_EXPRESSION;
	}  

	@Override
	public String evaluate(){
		return leftSide.getValue().equals(rightSide.getValue())
			? "T"
			: "NIL";
	}

	@Override
	public IFunction newInstance(ListNode listNode){
		return new EqFunction(listNode);
	}

	@Override
	public ExpressionKind getExpressionKind(){
		return ExpressionKind.LITERAL_EXPRESSION;
	}
}
