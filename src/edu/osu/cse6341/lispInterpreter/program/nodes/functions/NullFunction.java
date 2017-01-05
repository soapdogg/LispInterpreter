package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;
import edu.osu.cse6341.lispInterpreter.program.ExpressionKind;

public class NullFunction implements IFunction{

	public NullFunction(){}

	private NullFunction(ListNode listNode){}

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
		return new NullFunction(listNode);
	}

	@Override
	public ExpressionKind getExpressionKind(){
		return ExpressionKind.NUMERIC_EXPRESSION;
	}
}
