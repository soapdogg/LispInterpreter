package edu.osu.cse6341.lispInterpreter.program.nodes;

import java.util.Map;
import java.util.HashMap;

import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.program.Environment;
import edu.osu.cse6341.lispInterpreter.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.program.parser.Parser;
import edu.osu.cse6341.lispInterpreter.singleton.ComparatorSingleton;
import edu.osu.cse6341.lispInterpreter.singleton.FunctionSingleton;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;
import edu.osu.cse6341.lispInterpreter.functions.*;

public class ExpressionNode extends Node implements LispNode {

	private static final Map<String, LispFunction> functionMap;

	private Node address;
	private Node data;
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

	public ExpressionNode(Node address, Node data){
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
		address = (Node)parser.parseIntoNode(tokenizer);
        data = new ExpressionNode();
        data.parse(tokenizer);
	}

	@Override
	public Node evaluate(boolean areLiteralsAllowed) throws Exception{
	    if(this.address == null) return new AtomNode(false);

	    String addressValue = this.address.getValue();
	    Environment e = Environment.getEnvironment();
        if(e.isVariableName(addressValue)) return e.getVariableValue(addressValue);
        if(e.isFunctionName(addressValue)) return e.evaluateFunction(addressValue, this.data);
        if(functionMap.containsKey(addressValue)) return executeBuiltInFunction(addressValue);
        if(!areLiteralsAllowed) throw new Exception("Error! Invalid CAR value: " + addressValue + '\n');
        return this.address.evaluate(true);
	}

    @Override
    public String getValue() {
        return address == null ? ReservedValuesConstants.NIL : address.getValue() + ' ' + data.getValue();
    }

    @Override
    public boolean isList(){
        return isList;
    }

    @Override
    public String getListNotationToString(boolean isFirst){
        StringBuilder sb = new StringBuilder();
        if(isFirst) sb.append('(');
        sb.append(address.getListNotationToString(address.isList()));
        sb.append(getDataListNotationAsString());
        return sb.toString();
    }

    @Override
    public String getDotNotationToString() {
        return isList()
			? '(' + address.getDotNotationToString() + " . " + data.getDotNotationToString() + ')'
			: ReservedValuesConstants.NIL;
    }

    public int getLength(){
		return isList() ? data.getLength() + 1 : 0;
	}

	public Node getData(){
		return data;
	}

	public Node getAddress(){
	    return address;
	}

	private Node executeBuiltInFunction(String functionName) throws Exception{
        LispFunction function = functionMap.get(functionName);
        return function.evaluateLispFunction((LispNode)data);
    }

    private String getDataListNotationAsString(){
        if(!data.isList()) {
            String dataString = (((LispNode)data).isNodeNumeric() || nodeValueComparator.equalsT(data.getValue()))
                    ? (" . " + data.getListNotationToString(false))
                    : "";
            return dataString + ')';
        }
        else return ' ' + data.getListNotationToString(false);
    }

	@Override
	public Node evaluateLispNode(boolean areLiteralsAllowed) throws Exception {
		return evaluate(areLiteralsAllowed);
	}

	@Override
	public LispNode newLispNodeInstance() {
		return new ExpressionNode();
	}

	@Override
	public String getNodeValue() {
		return getValue();
	}

	@Override
	public boolean isNodeList() {
		return isList();
	}

	@Override
	public boolean isNodeNumeric() {
		return false;
	}

	@Override
	public int parameterLength() {
		return getLength();
	}
}
