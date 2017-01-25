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
	private String value;
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
	    value = Node.NIL;
	    address = null;
	    data = null;
	    isList = false;
	}

	public ExpressionNode(Node address, Node data){
	    this.address = address;
        this.data = data;

        StringBuilder valueStringBuilder = new StringBuilder();

		if(address.isList()) {
			valueStringBuilder.append('(');
			valueStringBuilder.append(((ExpressionNode)this.address).address.getValueToString());
			valueStringBuilder.append(')');
		}
		else {
			valueStringBuilder.append(this.address.getValueToString());
		}
        valueStringBuilder.append(' ');

        if(!data.isList() && ! equalsNil(data.getValueToString())) valueStringBuilder.append(". ");


        if(data.isList()){
            valueStringBuilder.append(((ExpressionNode)this.data).address.getValueToString());
        }else if(!equalsNil(this.data.getValueToString())){
            valueStringBuilder.append(this.data.getValueToString());
        }

        value = valueStringBuilder.toString();
        value = value.trim();

        isList = true;
        isNumeric = value.matches("-?[1-9][0-9]*|0");
    }

	@Override
	public void parse(Tokenizer tokenizer) throws Exception{
		TokenKind tokenKind = tokenizer.getCurrent().getTokenKind();
	    isList = tokenKind != TokenKind.CLOSE_TOKEN;
		if(!isList) return;

		address = Node.parseIntoNode(tokenizer);

        data = new ExpressionNode();
        data.parse(tokenizer);


		StringBuilder valueStringBuilder = new StringBuilder(address.getValueToString());
        if(data.isList()){
            valueStringBuilder.append(' ');
            valueStringBuilder.append(data.getValueToString());
        }
        value = valueStringBuilder.toString();
	}

	@Override
	public Node evaluate() throws Exception{
		if(!isList) return this;
		Node node = address.evaluate();
		String a = node.getValueToString();

		if(node.isNumeric() || equalsNil(a) || equalsT(a)) return node;
        else if(functionMap.containsKey(a)){
		    BaseFunction function = functionMap.get(a);
		    function = function.newInstance(data);
		    Node result = function.evaluate();
		    value = result.getValueToString();
		    isList = result.isList();
		    isNumeric = result.isNumeric();

		    return result;
        }
        else if(!node.isNumeric() && !node.isList() && !functionMap.containsKey(a)){
            StringBuilder sb = new StringBuilder("Error! Invalid CAR value: ");
            sb.append(a);
            sb.append('\n');
            throw new Exception(sb.toString());
        }
        return this;
	}

	@Override
	public Node newInstance(){
		return new ExpressionNode();
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
    public String getValueToString(){
	    return value;
    }

    @Override
    public String getDotNotationToString() {
        if(isList()){
            StringBuilder sb = new StringBuilder();
            sb.append('(');
            sb.append(address.getDotNotationToString());
            sb.append(" . ");
            sb.append(data.getDotNotationToString());
            sb.append(')');
            return sb.toString();
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
