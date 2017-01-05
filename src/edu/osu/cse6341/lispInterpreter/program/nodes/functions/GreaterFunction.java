package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;
import edu.osu.cse6341.lispInterpreter.program.ExpressionKind;

public class GreaterFunction implements IFunction{
	
	public GreaterFunction(){}

	private GreaterFunction(ListNode listNode){}

	@Override
	public boolean isDefinedCorrectly(){
		return false;
	}  

	@Override
	public String evaluate(){
		return "";
	}

	@Override
	public IFunction newInstance(ListNode listNode){
		return new GreaterFunction(listNode);
	}

	@Override
	public ExpressionKind getExpressionKind(){
		return ExpressionKind.NUMERIC_EXPRESSION;
	}
}
