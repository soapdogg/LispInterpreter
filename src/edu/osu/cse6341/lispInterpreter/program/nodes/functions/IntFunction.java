package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class IntFunction extends BaseFunction {

	private int length;
	private ListNode params;

	public IntFunction(){}

	private IntFunction(ListNode params){
		length = params.getLength();
		this.params  = params;
	}

	@Override
	public boolean hasError(){
		params.evaluate();
		return length == 2;
	}

	@Override
	public Node evaluate(){
        return new AtomNode(params.evaluate().isNumeric());
	}

    @Override
	public BaseFunction newInstance(ListNode params){
		return new IntFunction(params);
	}

}
