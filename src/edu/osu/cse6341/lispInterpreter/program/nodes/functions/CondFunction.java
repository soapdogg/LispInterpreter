package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;


public class CondFunction extends BaseFunction {

    private Node params;

	public CondFunction(){}

	private CondFunction(Node params){
        this.params = params;
    }

	@Override
	public boolean hasError(){
		return false;
	}  

	@Override
	public Node evaluate(){
        Node temp = ((ListNode)params).getAddress();
        if(!((ListNode)temp).getAddress().evaluate().getValueToString().equals("NIL")){
            return ((ListNode)temp).getData().evaluate();
        }else{
            Node listNode = ((ListNode)params).getData();
            CondFunction function = new CondFunction(listNode);
            return function.evaluate();
        }
	}

    @Override
	public BaseFunction newInstance(Node params){
		return new CondFunction(params);
	}

}
