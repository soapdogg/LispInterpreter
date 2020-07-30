package edu.osu.cse6341.lispInterpreter.program.nodes;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class AtomNode implements LispNode {

	private final String value;

	@Override
	public String getListNotationToString(boolean isFirst){
		return value;
	}

    @Override
    public String getNodeValue() {
        return value;
    }

    @Override
    public boolean isNodeList() {
        return false;
    }

    @Override
    public int parameterLength() {
        return 1;
    }
}
