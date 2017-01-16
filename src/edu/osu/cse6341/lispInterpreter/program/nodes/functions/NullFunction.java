package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class NullFunction extends BaseFunction {

	private int length;
	private ListNode params;

	public NullFunction(){}

	private NullFunction(ListNode params){
		length = params.getLength();
		this.params = params;
	}

	@Override
	public boolean hasError(){
		params.evaluate();
		return length == 2;
	}

	@Override
	public Node evaluate(){
		return new AtomNode(params.evaluate().getValueToString().equals("NIL"));
	}

    @Override
	public BaseFunction newInstance(ListNode params){
		return new NullFunction(params);
	}

}
