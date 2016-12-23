package edu.osu.cse6341.lispInterpreter.program.nodes;

public class ListNode implements IListNode{
	
	private IExpressionNode expressionNode;
	private IListNode listNode;

	@Override
	public void parse(){

	}

	@Override
	public String toString(){
		return "jackets";
	}

	@Override
	public IExpressionChild newInstance(){
		return new ListNode();
	}
}
