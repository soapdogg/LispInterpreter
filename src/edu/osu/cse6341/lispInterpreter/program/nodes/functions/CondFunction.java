package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class CondFunction extends BaseFunction {

	public CondFunction(){}

	private CondFunction(ListNode listNode){}

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
		return new CondFunction(listNode);
	}

}
