package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;
import edu.osu.cse6341.lispInterpreter.program.ExpressionKind;

public class AtomFunction implements IFunction{

	private int length;
	private ListNode child;

	public AtomFunction(){}

	private AtomFunction(ListNode listNode){
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
		return child.getLength() == 1 ? "T" : "NIL";
	}

	@Override
	public IFunction newInstance(ListNode listNode){
		return new AtomFunction(listNode);
	}

	@Override
	public ExpressionKind getExpressionKind(){
		return ExpressionKind.LITERAL_EXPRESSION;
	}
}
