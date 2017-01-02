package edu.osu.cse6341.lispInterpreter.program.nodes;

import java.util.Map;
import java.util.HashMap;

import edu.osu.cse6341.lispInterpreter.program.IParsable;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;
import edu.osu.cse6341.lispInterpreter.program.nodes.functions.*;

public class ListNode implements IExpressionChild{
	
	private ExpressionNode expressionNode;
	private ListNode listNode;
	private boolean isEmpty, isNumeric, isUndefined, isLiteral;
	private static Map<String, IFunction> functionMap;

	static{
		functionMap = new HashMap<>();
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
		listNode.evaluate();
		if(expressionNode.isLiteral()){} 
	}

	@Override
	public boolean isUndefined(){
		return isUndefined;
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
	public String toString(){
		return listNode.isEmpty() 
			? expressionNode.toString() 
			: expressionNode.toString() + " " +  listNode.toString(); 
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
}
