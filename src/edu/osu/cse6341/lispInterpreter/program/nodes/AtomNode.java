package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.program.Environment;
import edu.osu.cse6341.lispInterpreter.program.IEvaluatable;
import edu.osu.cse6341.lispInterpreter.program.IParsable;
import edu.osu.cse6341.lispInterpreter.program.IPrettyPrintable;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;

public class AtomNode implements LispNode, IParsable, IEvaluatable, IPrettyPrintable {
	
	private String value;

	public AtomNode(){
	    value = ReservedValuesConstants.NIL;
    }

    public AtomNode(boolean value){
	    this.value = value ? ReservedValuesConstants.T : ReservedValuesConstants.NIL;
    }

    public AtomNode(int value){
        this.value = Integer.toString(value);
    }

	@Override
	public void parse(Tokenizer tokenizer) throws Exception{
		IToken token = tokenizer.getNextToken();
		value = token.toString();
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
    public LispNode newLispNodeInstance() {
        return new AtomNode();
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
