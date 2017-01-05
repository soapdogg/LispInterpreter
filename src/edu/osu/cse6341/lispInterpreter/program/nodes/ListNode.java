package edu.osu.cse6341.lispInterpreter.program.nodes;

import java.util.Map;
import java.util.HashMap;

import edu.osu.cse6341.lispInterpreter.program.ExpressionKind;
import edu.osu.cse6341.lispInterpreter.program.IParsable;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;
import edu.osu.cse6341.lispInterpreter.program.nodes.functions.*;

public class ListNode implements IExpressionChild{
	
	private ExpressionNode expressionNode;
	private ListNode listNode;
	private boolean isEmpty; 
	private static Map<String, IFunction> functionMap;
	private String value;
	private ExpressionKind expressionKind;

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

	@Override
	public void parse(){
		isEmpty = Tokenizer.getTokenizer().getCurrent().getTokenKind() == TokenKind.CLOSE_TOKEN;
		if(isEmpty) return;
		expressionNode = new ExpressionNode();
		listNode = new ListNode();
		expressionNode.parse();
		listNode.parse();
	}

	@Override
	public void evaluate(){
		if(isEmpty) return;
		expressionNode.evaluate();
		if(expressionNode.getExpressionKind() == ExpressionKind.LITERAL_EXPRESSION){
			IFunction function = functionMap.get(expressionNode.toString());
			function = function.newInstance(this);
			boolean isUndefined = !function.isDefinedCorrectly();
			if(isUndefined) {
				expressionKind = ExpressionKind.UNDEFINED_EXPRESSION;
			}else{
				value = function.evaluate();
				expressionKind = function.getExpressionKind();
			}
		}else if(expressionNode.getExpressionKind() == ExpressionKind.NUMERIC_EXPRESSION){
			value = expressionNode.toString();
			expressionKind = ExpressionKind.NUMERIC_EXPRESSION;
		} 
	}

	@Override
	public String toString(){
		return value;
	}

	@Override
	public IExpressionChild newInstance(){
		return new ListNode();
	}

	public int getLength(){
		return isEmpty ? 0 : listNode.getLength() + 1;
	}

	public boolean isEmpty(){
		return isEmpty;
	}

	public ListNode getListNode(){
		return listNode;
	}

	public ExpressionKind getExpressionKind(){
		return expressionKind;
	}
}
