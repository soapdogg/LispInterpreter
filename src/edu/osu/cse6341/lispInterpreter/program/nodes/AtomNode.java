package edu.osu.cse6341.lispInterpreter.program.nodes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "newInstance")
public class AtomNode implements LispNode {

	private final String value;
}
