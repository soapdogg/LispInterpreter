package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class ExpressionNode implements LispNode {

	private final LispNode address;
	private final LispNode data;
	private final boolean isList;

	public LispNode getData(){
		return data;
	}

	public LispNode getAddress(){
	    return address;
	}

	@Override
	public String getNodeValue() {
		return address == null
			? ReservedValuesConstants.NIL
			: address.getNodeValue() + ' ' + data.getNodeValue();
	}

	@Override
	public boolean isNodeList() {
		return isList;
	}

	@Override
	public int parameterLength() {
		return isNodeList() ? data.parameterLength() + 1 : 0;
	}
}
