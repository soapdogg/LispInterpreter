package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class QuoteFunction extends BaseFunction {

    private ListNode params;

	public QuoteFunction(){}

	private QuoteFunction(ListNode params){
	    this.params = params;
    }

	@Override
	public boolean hasError(){
		return false;
	}  

	@Override
	public Node evaluate(){
        return params.getAddress();
	}

    @Override
	public BaseFunction newInstance(ListNode params){
		return new QuoteFunction(params);
	}

}
