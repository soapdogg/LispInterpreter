package edu.osu.cse6341.lispInterpreter.datamodels;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "newInstance")
public class ExpressionNode implements Node {

	private final Node address;
	private final Node data;
}
