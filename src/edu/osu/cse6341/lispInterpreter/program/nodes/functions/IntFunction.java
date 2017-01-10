package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;

public class IntFunction implements IFunction{

	private int length;
	private ListNode child;
	private String value;

	public IntFunction(){}

	private IntFunction(ListNode listNode){
		length = listNode.getLength();
		child  = listNode.getListNode();
	}

	@Override
	public boolean isDefinedCorrectly(){
		child.evaluate();
		return length == 2;
			//&& child.getExpressionKind() != ExpressionKind.UNDEFINED_EXPRESSION;
	}  

	@Override
	public void evaluate(){
		//value = child.getExpressionKind() == ExpressionKind.NUMERIC_EXPRESSION
		//	? "T"
		//	: "NIL";
	}
    @Override
    public String getValue() {
        return value;
    }
	@Override
	public IFunction newInstance(ListNode listNode){
		return new IntFunction(listNode);
	}

}
