package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class CdrFunction extends BaseFunction {

    ExpressionNode address;

	public CdrFunction(){}

	private CdrFunction(ListNode params){
	    address = params.getAddress();
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
	public BaseFunction newInstance(ListNode params){
		return new CdrFunction(params);
	}

}
