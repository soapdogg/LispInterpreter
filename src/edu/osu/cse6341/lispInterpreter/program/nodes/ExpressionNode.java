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
	private boolean isList, isNumeric;

	static{
		functionMap = new HashMap<>();
		functionMap.put("ATOM", new AtomFunction());
		functionMap.put("CAR", new CarFunction());
		functionMap.put("CDR", new CdrFunction());
		functionMap.put("COND", new CondFunction());
		functionMap.put("CONS", new ConsFunction());
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
	    address = null;
	    data = null;
	    isList = false;
	    isNumeric = false;
	}

	public ExpressionNode(Node address, Node data){
	    this.address = address;
        this.data = data;
        this.isList = true;
        isNumeric = false;
    }

	@Override
	public void parse(Tokenizer tokenizer) throws Exception{
		TokenKind tokenKind = tokenizer.getCurrent().getTokenKind();
	    isList = tokenKind != TokenKind.CLOSE_TOKEN;
		if(!isList) return;
		address = Node.parseIntoNode(tokenizer);
        data = new ExpressionNode();
        data.parse(tokenizer);
        isNumeric = address.isNumeric() && !data.isList();
	}

	@Override
	public Node evaluate(boolean areNumbersAllowed) throws Exception{
        if (isList) {
            Node node = address.evaluate(true);
            String a = node.getValue();

            if ((node.isNumeric() && areNumbersAllowed) || equalsNil(a) || equalsT(a)) return node;
            else if (functionMap.containsKey(a))
            {
                BaseFunction function = functionMap.get(a);
                function = function.newInstance(data);
                Node result = function.evaluate();
                isList = result.isList();
                isNumeric = result.isNumeric();

                return result;
            }
            else if (!node.isList() && !functionMap.containsKey(a))
            {
                StringBuilder sb = new StringBuilder("Error! Invalid CAR value: ");
                sb.append(a);
                sb.append('\n');
                throw new Exception(sb.toString());
            }
        }
        return this;
	}

	@Override
	public Node newInstance(){
		return new ExpressionNode();
	}

    @Override
    public String getValue() {
	    if(address == null) return "NIL";
        StringBuilder sb = new StringBuilder();
        sb.append(address.getValue());
        if(data != null) {
            sb.append(' ');
            sb.append(data.getValue());
        }

        return sb.toString();
    }

    @Override
    public boolean isList(){
        return isList;
    }

    @Override
    public boolean isNumeric(){
        return isNumeric;
    }

    @Override
    public String getListNotationToString(boolean isFirst){
        StringBuilder sb = new StringBuilder();
        if(isFirst) sb.append('(');
        if(address != null) {
            sb.append(address.getListNotationToString(address.isList()));
        }
        if(data != null) {
            if(!data.isList()) {
                if(data.isNumeric() || equalsT(data.getValue())) {
                    sb.append(" . ");
                    sb.append(data.getListNotationToString(false));
                    sb.append(')');
                }
                else sb.append(')');
            }
            else{
                sb.append(' ');
                sb.append(data.getListNotationToString(false));
            }
        }
        return sb.toString();
    }

    @Override
    public String getDotNotationToString() {
        if(isList()){
            return "(" +
                    address.getDotNotationToString() +
                    " . " +
                    data.getDotNotationToString() +
                    ')';
        }
        return Node.NIL;
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

}
