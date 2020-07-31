package edu.osu.cse6341.lispInterpreter.program.nodes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "newInstance")
public class ExpressionNode implements LispNode {

	private final LispNode address;
	private final LispNode data;

	@Override
	public String getNodeValue() {
		return address.getNodeValue() + ' ' + data.getNodeValue();
	}

	@Override
	public int parameterLength() {
		return data.parameterLength() + 1;
	}
}
