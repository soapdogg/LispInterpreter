package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.program.Environment;
import edu.osu.cse6341.lispInterpreter.program.IEvaluatable;
import edu.osu.cse6341.lispInterpreter.program.IPrettyPrintable;

public class AtomNode implements LispNode, IEvaluatable, IPrettyPrintable {

	private final String value;

    public AtomNode(boolean value){
	    this.value = value ? ReservedValuesConstants.T : ReservedValuesConstants.NIL;
    }

    public AtomNode(int value){
        this.value = Integer.toString(value);
    }

    public AtomNode(String value) {
	    this.value = value;
    }

	@Override
	public LispNode evaluate(boolean areLiteralsAllowed){
	    if(Environment.getEnvironment().isVariableName(value)) return Environment.getEnvironment().getVariableValue(value);
        return this;
	}

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
