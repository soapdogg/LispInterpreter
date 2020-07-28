package edu.osu.cse6341.lispInterpreter.program.nodes;

import java.util.Map;
import java.util.HashMap;

import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.program.Environment;
import edu.osu.cse6341.lispInterpreter.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.program.IEvaluatable;
import edu.osu.cse6341.lispInterpreter.program.IParsable;
import edu.osu.cse6341.lispInterpreter.program.IPrettyPrintable;
import edu.osu.cse6341.lispInterpreter.program.parser.Parser;
import edu.osu.cse6341.lispInterpreter.singleton.ComparatorSingleton;
import edu.osu.cse6341.lispInterpreter.singleton.FunctionSingleton;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;
import edu.osu.cse6341.lispInterpreter.functions.*;

public class ExpressionNode implements LispNode, IParsable, IEvaluatable, IPrettyPrintable {

	private static final Map<String, LispFunction> functionMap;

	private LispNode address;
	private LispNode data;
	private boolean isList;
	private final NodeValueComparator nodeValueComparator;
	private final Parser parser;

	static{
		functionMap = new HashMap<>();
		functionMap.put(FunctionNameConstants.ATOM, FunctionSingleton.INSTANCE.getAtomFunction());
		functionMap.put(FunctionNameConstants.CAR, FunctionSingleton.INSTANCE.getCarFunction());
		functionMap.put(FunctionNameConstants.CDR, FunctionSingleton.INSTANCE.getCdrFunction());
		functionMap.put(FunctionNameConstants.COND, FunctionSingleton.INSTANCE.getCondFunction());
		functionMap.put(FunctionNameConstants.CONS, FunctionSingleton.INSTANCE.getConsFunction());
		functionMap.put(FunctionNameConstants.DEFUN, FunctionSingleton.INSTANCE.getDefunFunction());
		functionMap.put(FunctionNameConstants.EQ, FunctionSingleton.INSTANCE.getEqFunction());
		functionMap.put(FunctionNameConstants.GREATER, FunctionSingleton.INSTANCE.getGreaterFunction());
		functionMap.put(FunctionNameConstants.INT, FunctionSingleton.INSTANCE.getIntFunction());
		functionMap.put(FunctionNameConstants.LESS, FunctionSingleton.INSTANCE.getLessFunction());
		functionMap.put(FunctionNameConstants.MINUS, FunctionSingleton.INSTANCE.getMinusFunction());
		functionMap.put(FunctionNameConstants.NULL, FunctionSingleton.INSTANCE.getNullFunction());
		functionMap.put(FunctionNameConstants.PLUS, FunctionSingleton.INSTANCE.getPlusFunction());
		functionMap.put(FunctionNameConstants.QUOTE, FunctionSingleton.INSTANCE.getQuoteFunction());
		functionMap.put(FunctionNameConstants.TIMES, FunctionSingleton.INSTANCE.getTimesFunction());
	}

	public ExpressionNode(){
		nodeValueComparator = ComparatorSingleton.INSTANCE.getNodeValueComparator();
		parser = new Parser();
	}

	public ExpressionNode(
		LispNode address,
		LispNode data
	){
	    this.address = address;
        this.data = data;
        this.isList = true;
        nodeValueComparator = ComparatorSingleton.INSTANCE.getNodeValueComparator();
        parser = new Parser();
    }

	@Override
	public void parse(Tokenizer tokenizer) throws Exception{
		TokenKind tokenKind = tokenizer.getCurrent().getTokenKind();
	    isList = tokenKind != TokenKind.CLOSE_TOKEN;
		if(!isList) return;
		address = parser.parseIntoNode(tokenizer);
        data = new ExpressionNode();
        data.parse(tokenizer);
	}

	@Override
	public LispNode evaluate(boolean areLiteralsAllowed) throws Exception{
	    if(this.address == null) return new AtomNode(false);

	    String addressValue = this.address.getNodeValue();
	    Environment e = Environment.getEnvironment();
        if(e.isVariableName(addressValue)) return e.getVariableValue(addressValue);
        if(e.isFunctionName(addressValue)) return e.evaluateFunction(addressValue, this.data);
        if(functionMap.containsKey(addressValue)) return executeBuiltInFunction(addressValue);
        if(!areLiteralsAllowed) throw new Exception("Error! Invalid CAR value: " + addressValue + '\n');
        return this.address.evaluate(true);
	}

    @Override
    public String getListNotationToString(boolean isFirst){
        StringBuilder sb = new StringBuilder();
        if(isFirst) sb.append('(');
        sb.append(address.getListNotationToString(address.isNodeList()));
        sb.append(getDataListNotationAsString());
        return sb.toString();
    }

    @Override
    public String getDotNotationToString() {
        return isNodeList()
			? '(' + address.getDotNotationToString() + " . " + data.getDotNotationToString() + ')'
			: ReservedValuesConstants.NIL;
    }

	public LispNode getData(){
		return data;
	}

	public LispNode getAddress(){
	    return address;
	}

	private LispNode executeBuiltInFunction(String functionName) throws Exception{
        LispFunction function = functionMap.get(functionName);
        return function.evaluateLispFunction(data);
    }

    private String getDataListNotationAsString(){
        if(!data.isNodeList()) {
            String dataString = (data.isNodeNumeric() || nodeValueComparator.equalsT(data.getNodeValue()))
                    ? (" . " + data.getListNotationToString(false))
                    : "";
            return dataString + ')';
        }
        else return ' ' + data.getListNotationToString(false);
    }

	@Override
	public LispNode evaluateLispNode(boolean areLiteralsAllowed) throws Exception {
		return evaluate(areLiteralsAllowed);
	}

	@Override
	public LispNode newLispNodeInstance() {
		return new ExpressionNode();
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
	public boolean isNodeNumeric() {
		return false;
	}

	@Override
	public int parameterLength() {
		return isNodeList() ? data.parameterLength() + 1 : 0;
	}
}
