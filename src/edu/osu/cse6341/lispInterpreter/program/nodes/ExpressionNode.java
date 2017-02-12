package edu.osu.cse6341.lispInterpreter.program.nodes;

import java.util.Map;
import java.util.HashMap;

import edu.osu.cse6341.lispInterpreter.program.Environment;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;
import edu.osu.cse6341.lispInterpreter.program.nodes.functions.*;

public class ExpressionNode extends Node{

	private static final Map<String, BaseFunction> functionMap;

	private Node address;
	private Node data;
	private boolean isList;

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

	public ExpressionNode(){}

	public ExpressionNode(Node address, Node data){
	    this.address = address;
        this.data = data;
        this.isList = true;
    }

	@Override
	public void parse(Tokenizer tokenizer) throws Exception{
		TokenKind tokenKind = tokenizer.getCurrent().getTokenKind();
	    isList = tokenKind != TokenKind.CLOSE_TOKEN;
		if(!isList) return;
		address = Node.parseIntoNode(tokenizer);
        data = new ExpressionNode();
        data.parse(tokenizer);
	}

	@Override
	public Node evaluate(boolean areNumbersAllowed) throws Exception{
        if (isList) {
            Node node = this.address.evaluate(true);
            String addressEvaluatedValue = node.getValue();
            if ((node.isNumeric() && areNumbersAllowed) || equalsNil(addressEvaluatedValue) || equalsT(addressEvaluatedValue)) return node;
            else if (functionMap.containsKey(addressEvaluatedValue)) return executeBuiltInFunction(addressEvaluatedValue);
            else if (Environment.isFunctionName(addressEvaluatedValue)){

            }
            else if (!node.isList() && !functionMap.containsKey(addressEvaluatedValue)) throw new Exception("Error! Invalid CAR value: " + addressEvaluatedValue + '\n');
        }
        return this;
	}

	@Override
	public Node newInstance(){
		return new ExpressionNode();
	}

    @Override
    public String getValue() {
        return address == null ? "NIL" : address.getValue() + ' ' + data.getValue();
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
        return isList() ? '(' + address.getDotNotationToString() + " . " + data.getDotNotationToString() + ')' : Node.NIL;
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
        BaseFunction function = functionMap.get(functionName);
        function = function.newInstance(data);
        return function.evaluate();
    }

    private String getDataListNotationAsString(){
        if(!data.isList()) {
            String dataString = (data.isNumeric() || equalsT(data.getValue()))
                    ? (" . " + data.getListNotationToString(false))
                    : "";
            return dataString + ')';
        }
        else return ' ' + data.getListNotationToString(false);
    }
}
