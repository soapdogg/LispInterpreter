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
	private boolean isList, isNumeric, isLiteral;

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
	    value = "NIL";
	    address = null;
	    data = null;
	    isList = false;
	}

    private ExpressionNode(AtomNode atomNode){
	    address = atomNode;
	    value = address.getValueToString();
	    isList = false;
	    data = null;
    }

	public ExpressionNode(Node address, Node data){
	    this.address = address;
        this.data = data.isList() ? (ExpressionNode) data : new ExpressionNode((AtomNode) data);
        value = this.address.getValueToString() + " ";
	    if(!(data.isList() || this.data.getValueToString().equals("NIL"))) value += ". ";
        value += data.isList() ? ((ExpressionNode) this.data).address.getValueToString() : this.data.getValueToString().equals("NIL")
                ? "" : this.data.getValueToString();
        value = value.trim();
	    isList = true;
        isNumeric = !(data.isList() || !this.data.getValueToString().equals("NIL") || !value.matches(
                "-?[1-9][0-9]*|0"));
        isLiteral = !isList && !isNumeric;
    }

	@Override
	public void parse(Tokenizer tokenizer) throws Exception{
		TokenKind tokenKind = tokenizer.getCurrent().getTokenKind();
	    isList = tokenKind != TokenKind.CLOSE_TOKEN;
		if(!isList()) return;

		address = Node.parseIntoNode(tokenizer);

        data = new ExpressionNode();
        data.parse(tokenizer);

		value = address.getValueToString();
        if(data.isList()) value += " " + data.getValueToString();
	}

	@Override
	public Node evaluate() throws Exception{
		if(!isList()) return null;
		Node node = address.evaluate();
		String a = node.getValueToString();

		if(node.isNumeric())
		    return node;
		else if (a.equals("NIL")) return new AtomNode("NIL");
		else if (a.equals("T")) return new AtomNode("T");
        else if(functionMap.containsKey(a)){
		    BaseFunction function = functionMap.get(a);
		    function = function.newInstance(data);
		    Node result = function.evaluate();
		    value = result.getValueToString();
		    isList = result.isList();
		    isNumeric = value.matches("-?[1-9][0-9]*|0");
		    isLiteral = value.matches("[A-Z][A-Z0-9]*");

		    return result;
        }
        else if(!node.isNumeric() && !node.isList() && !functionMap.containsKey(a)){
            throw new Exception("Error! Invalid CAR value: " + a);
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
    public boolean isLiteral(){
        return isLiteral;
    }

    @Override
    public String getValueToString(){
	    return value;
    }

    @Override
    public String getDotNotationToString() {
        return isList() ? "(" + address.getDotNotationToString() + " . " + data.getDotNotationToString() + ")" : "NIL";
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
