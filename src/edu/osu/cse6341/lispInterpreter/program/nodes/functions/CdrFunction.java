package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class CdrFunction extends BaseFunction {

	public CdrFunction(){}

	private CdrFunction(ListNode listNode){}

	@Override
	public boolean hasError(){
		return false;
	}  

	@Override
	public Node evaluate(){
        return null;
	}

    @Override
	public BaseFunction newInstance(ListNode listNode){
		return new CdrFunction(listNode);
	}

}
