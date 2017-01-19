package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class ConsFunction extends BaseFunction {

    private Node leftSide, rightSide;
    private int length;

	public ConsFunction(){}

	private ConsFunction(Node params){
        length = params.getLength();
        leftSide = params;
        rightSide = ((ListNode)leftSide).getData();
    }

	@Override
	public boolean hasError(){
		return false;
	}  

	@Override
	public Node evaluate(){
        Node leftResult = leftSide.evaluate();
        Node rightResult = rightSide.evaluate();
        return new ListNode(leftResult, rightResult);
	}

    @Override
	public BaseFunction newInstance(Node params){
		return new ConsFunction(params);
	}

}
