package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;

public class CdrFunction implements IFunction{

    private String value;

	public CdrFunction(){}

	private CdrFunction(ListNode listNode){}

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
		return new CdrFunction(listNode);
	}

}
