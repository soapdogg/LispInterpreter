package edu.osu.cse6341.lispInterpreter.program.nodes;

import java.util.Map;
import java.util.HashMap;

import edu.osu.cse6341.lispInterpreter.program.Program;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;
import edu.osu.cse6341.lispInterpreter.program.nodes.functions.*;

public class ListNode implements IExpressionChild{
	
	private ExpressionNode expressionNode;
	private ListNode listNode;
	private boolean isEmpty; 
	private static Map<String, IFunction> functionMap;
	private String value;

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
	    isEmpty = tokenKind == TokenKind.CLOSE_TOKEN;
		if(isEmpty) return;
		expressionNode = new ExpressionNode();
		listNode = new ListNode();
		expressionNode.parse(tokenizer, program);
		if(program.hasError()) return;
		listNode.parse(tokenizer, program);
	}

	@Override
	public void evaluate(){
		if(isEmpty) return;
		expressionNode.evaluate();
		if(functionMap.containsKey(expressionNode.getValue())){
			IFunction function = functionMap.get(expressionNode.getValue());
			function = function.newInstance(this);
			boolean isUndefined = !function.isDefinedCorrectly();
			if(isUndefined) {
			}else{
				value = function.getValue();
			}
		}else {
			value = expressionNode.getValue();
		}
	}

	@Override
	public IExpressionChild newInstance(){
		return new ListNode();
	}

	@Override
    public String getValue(){
	    return value;
    }

    @Override
    public String getDotNotation() {
        return isEmpty ? "NIL" : "(" + expressionNode.getDotNotation() + " . " + listNode.getDotNotation() + ")";
    }

    public int getLength(){
		return isEmpty ? 0 : listNode.getLength() + 1;
	}

	public ListNode getListNode(){
		return listNode;
	}

}
