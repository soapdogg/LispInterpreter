package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class CarFunction extends BaseFunction {

    ExpressionNode address;

	public CarFunction(){}

	private CarFunction(ListNode params){
        address = params.getAddress();
	}

	@Override
	public boolean hasError(){
		return false;
	}  

	@Override
	public Node evaluate(){
        return ((ListNode)address.evaluate()).getAddress();
	}

    @Override
	public BaseFunction newInstance(ListNode params){
		return new CarFunction(params);
	}

}
