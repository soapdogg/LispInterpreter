package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;
import edu.osu.cse6341.lispInterpreter.program.ExpressionKind;

public class GreaterFunction implements IFunction{
	
	private int length;
	private ListNode leftSide, rightSide;

	public GreaterFunction(){}

	private GreaterFunction(ListNode listNode){
		length = listNode.getLength();
		leftSide = listNode.getListNode();
		if(leftSide != null) rightSide = leftSide.getListNode();
	}

	@Override
	public boolean isDefinedCorrectly(){
		leftSide.evaluate();
		rightSide.evaluate();
		return length == 3 
			&& leftSide.getExpressionKind() == ExpressionKind.NUMERIC_EXPRESSION  
			&& rightSide.getExpressionKind() == ExpressionKind.NUMERIC_EXPRESSION;
	}  

	@Override
	public String evaluate(){
		int left = Integer.parseInt(leftSide.toString());
		int right = Integer.parseInt(rightSide.toString());
		return left > right ? "T" : "NIL"; 
	}

	@Override
	public IFunction newInstance(ListNode listNode){
		return new GreaterFunction(listNode);
	}

	@Override
	public ExpressionKind getExpressionKind(){
		return ExpressionKind.LITERAL_EXPRESSION;
	}
}
