package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;

public class ConsFunction implements IFunction{

    private String value;

	public ConsFunction(){}

	private ConsFunction(ListNode listNode){}

	@Override
	public boolean isDefinedCorrectly(){
		return false;
	}  

	@Override
	public void evaluate(){

	}

    @Override
    public String getValue() {
        return value;
    }

	@Override
	public IFunction newInstance(ListNode listNode){
		return new ConsFunction(listNode);
	}

}
