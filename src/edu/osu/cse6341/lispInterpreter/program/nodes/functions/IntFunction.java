package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;
import edu.osu.cse6341.lispInterpreter.program.ExpressionKind;

public class IntFunction implements IFunction{

	private int length;
	private ListNode child;

	public IntFunction(){}

	private IntFunction(ListNode listNode){
		length = listNode.getLength();
		child  = listNode.getListNode();
	}

	@Override
	public boolean isDefinedCorrectly(){
		child.evaluate();
		return length == 2 
			&& child.getExpressionKind() != ExpressionKind.UNDEFINED_EXPRESSION; 
	}  

	@Override
	public String evaluate(){
		return child.getExpressionKind() == ExpressionKind.NUMERIC_EXPRESSION
			? "T"
			: "NIL";
	}

	@Override
	public IFunction newInstance(ListNode listNode){
		return new IntFunction(listNode);
	}

	@Override
	public ExpressionKind getExpressionKind(){
		return ExpressionKind.LITERAL_EXPRESSION;
	}
}
