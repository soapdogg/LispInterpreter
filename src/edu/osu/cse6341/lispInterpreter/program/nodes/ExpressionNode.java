package edu.osu.cse6341.lispInterpreter.program.nodes;

import java.util.Map;
import java.util.HashMap;

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
		functionMap.put("EQ", new EqFunction());
		functionMap.put("INT", new IntFunction());
		functionMap.put("LESS", new LessFunction());
		functionMap.put("NULL", new NullFunction());
		functionMap.put("PLUS", new PlusFunction());
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
	public Node evaluate(boolean areLiteralsAllowed) throws Exception{
	    if(this.address == null) return new AtomNode();

	    String addressValue = this.address.getValue();
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
