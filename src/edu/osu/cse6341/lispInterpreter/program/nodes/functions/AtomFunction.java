package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;

public class AtomFunction implements IFunction{

	private int length;
	private ListNode child;
    private String value;
    private boolean isDefinedCorrectly;

	public AtomFunction(){}

	private AtomFunction(ListNode listNode){
		length = listNode.getLength();
		child  = listNode.getListNode();
	}

	@Override
	public boolean isDefinedCorrectly(){
		child.evaluate();
		return length == 2;
			// && child.isDefinedCorrectly();
	}  

	@Override
	public void evaluate(){
		value = child.getLength() == 1 ? "T" : "NIL";
	}

    @Override
    public String getValue() {
        return value;
    }

    @Override
	public IFunction newInstance(ListNode listNode){
		return new AtomFunction(listNode);
	}

}
