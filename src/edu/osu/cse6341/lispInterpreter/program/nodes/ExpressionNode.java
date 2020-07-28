package edu.osu.cse6341.lispInterpreter.program.nodes;

import java.util.Map;
import java.util.HashMap;

import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.program.Environment;
import edu.osu.cse6341.lispInterpreter.program.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.program.parser.Parser;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;
import edu.osu.cse6341.lispInterpreter.program.functions.*;

public class ExpressionNode extends Node implements LispNode {

	private static final Map<String, LispFunction> functionMap;

	private Node address;
	private Node data;
	private boolean isList;
	private final NodeValueComparator nodeValueComparator;
	private final Parser parser;

	static{
		functionMap = new HashMap<>();
		functionMap.put("ATOM", new AtomFunction());
		functionMap.put("CAR", new CarFunction());
		functionMap.put("CDR", new CdrFunction());
		functionMap.put("COND", new CondFunction());
		functionMap.put("CONS", new ConsFunction());
		functionMap.put("DEFUN", new DefunFunction());
		functionMap.put("EQ", new EqFunction());
		functionMap.put("GREATER", new GreaterFunction());
		functionMap.put("INT", new IntFunction());
		functionMap.put("LESS", new LessFunction());
		functionMap.put("MINUS", new MinusFunction());
		functionMap.put("NULL", new NullFunction());
		functionMap.put("PLUS", new PlusFunction());
		functionMap.put("QUOTE", new QuoteFunction());
		functionMap.put("TIMES", new TimesFunction());
	}

	public ExpressionNode(){
		nodeValueComparator = new NodeValueComparator();
		parser = new Parser();
	}

	public ExpressionNode(Node address, Node data){
	    this.address = address;
        this.data = data;
        this.isList = true;
        nodeValueComparator = new NodeValueComparator();
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
	public Node newInstance(){
		return new ExpressionNode();
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
    public boolean isNumeric(){
        return false;
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
        return function.evaluateLispFunction(data);
    }

    private String getDataListNotationAsString(){
        if(!data.isList()) {
            String dataString = (data.isNumeric() || nodeValueComparator.equalsT(data.getValue()))
                    ? (" . " + data.getListNotationToString(false))
                    : "";
            return dataString + ')';
        }
        else return ' ' + data.getListNotationToString(false);
    }

	@Override
	public int parameterLength() {
		return getLength();
	}
}
