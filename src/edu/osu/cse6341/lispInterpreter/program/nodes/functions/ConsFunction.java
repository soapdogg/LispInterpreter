package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;
import edu.osu.cse6341.lispInterpreter.program.ExpressionKind;

public class ConsFunction implements IFunction{

	public ConsFunction(){}

	private ConsFunction(ListNode listNode){}

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
		return new ConsFunction(listNode);
	}

	@Override
	public ExpressionKind getExpressionKind(){
		return ExpressionKind.NUMERIC_EXPRESSION;
	}
}
