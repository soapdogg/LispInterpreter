package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class QuoteFunction extends BaseFunction {

    private Node params;

	public QuoteFunction(){}

	private QuoteFunction(Node params){
	    this.params = params;
    }

    @Override
	public Node evaluate(){
        return ((ExpressionNode)params).getAddress();
	}

    @Override
	public BaseFunction newInstance(Node params){
		return new QuoteFunction(params);
	}

}
