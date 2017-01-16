package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class CarFunction extends BaseFunction {

	public CarFunction(){}

	private CarFunction(ListNode listNode){
		
	}

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
		return new CarFunction(listNode);
	}

}
