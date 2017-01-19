package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class CarFunction extends BaseFunction {

    private Node address;

	public CarFunction(){}

	private CarFunction(Node params){
        address = ((ListNode)params).getAddress();
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
	public BaseFunction newInstance(Node params){
		return new CarFunction(params);
	}

}
