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
    public String getDotNotationToString() {
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
    public boolean isNodeNumeric() {
        return value.matches("-?[1-9][0-9]*|0");
    }

    @Override
    public int parameterLength() {
        return 1;
    }
}
