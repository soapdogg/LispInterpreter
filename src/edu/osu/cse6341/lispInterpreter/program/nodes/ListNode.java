package edu.osu.cse6341.lispInterpreter.program.nodes;

import java.util.Map;
import java.util.HashMap;

import edu.osu.cse6341.lispInterpreter.program.Program;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;
import edu.osu.cse6341.lispInterpreter.program.nodes.functions.*;

public class ListNode extends Node{
	
	private ExpressionNode address;
	private ListNode data;
	private static Map<String, BaseFunction> functionMap;
	private String value;
	private boolean isList;

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

	public ListNode(){
	    value = "NIL";
	}

	@Override
	public void parse(Tokenizer tokenizer, Program program){
		TokenKind tokenKind = tokenizer.getCurrent().getTokenKind();
	    isList = tokenKind != TokenKind.CLOSE_TOKEN;
		if(!isList()) return;
		address = new ExpressionNode();
		data = new ListNode();
		address.parse(tokenizer, program);
		if(program.hasError()) return;
		data.parse(tokenizer, program);
	}

	@Override
	public Node evaluate(){
		if(!isList()) return null;
		String a = address.evaluate().getValueToString();
		ListNode params;

		if(address.isNumeric()) return address.evaluate();
		else if (a.matches("NIL")) return new AtomNode("NIL");
		else if (a.matches("T")) return new AtomNode("T");
		else if(a.matches("CAR") || a.matches("CDR")){
		    ListNode s;
        }
        else if(functionMap.containsKey(a)){
		    BaseFunction function = functionMap.get(a);
		    function = function.newInstance(data);
		    Node result = function.evaluate();
		    value = result.getValueToString();
		    return result;
        }
        return null;
	}

	@Override
	public Node newInstance(){
		return new ListNode();
	}

    @Override
    public boolean isList(){
        return isList;
    }

    @Override
    public boolean isNumeric(){
        return address.isNumeric();
    }

    @Override
    public boolean isLiteral(){
        return address.isLiteral();
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

	public ListNode getData(){
		return data;
	}

}
