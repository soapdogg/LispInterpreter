package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class CdrFunction extends BaseFunction {

    private Node address;

	public CdrFunction(){}

	private CdrFunction(Node params){
	    address = ((ListNode) params).getAddress();
    }

	@Override
	public boolean hasError(){
		return false;
	}  

	@Override
	public Node evaluate(){
        return ((ListNode)address.evaluate()).getData();
	}

    @Override
	public BaseFunction newInstance(Node params){
		return new CdrFunction(params);
	}

}
