package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;


public class CondFunction extends BaseFunction {

    private Node params;

	public CondFunction(){}

	private CondFunction(Node params){
        this.params = params;
    }

    @Override
	public Node evaluate(){
        Node temp = ((ExpressionNode)params).getAddress();
        if(!((ExpressionNode)temp).getAddress().evaluate().getValueToString().equals("NIL")){
            return ((ExpressionNode)temp).getData().evaluate();
        }else{
            Node listNode = ((ExpressionNode)params).getData();
            CondFunction function = new CondFunction(listNode);
            return function.evaluate();
        }
	}

    @Override
	public BaseFunction newInstance(Node params){
		return new CondFunction(params);
	}

}
