package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

import java.util.List;

public class CondFunction extends BaseFunction {

    private ListNode params;


	public CondFunction(){}

	private CondFunction(ListNode params){
        this.params = params;
    }

	@Override
	public boolean hasError(){
		return false;
	}  

	@Override
	public Node evaluate(){
        ExpressionNode temp = params.getAddress();
        ListNode bool = (ListNode) temp.getExpressionChild();
        if(!bool.getAddress().evaluate().getValueToString().equals("NIL")){
            return bool.getData().evaluate();
        }else{
            CondFunction function = new CondFunction(params.getData());
            return function.evaluate();
        }
	}

    @Override
	public BaseFunction newInstance(ListNode params){
		return new CondFunction(params);
	}

}
