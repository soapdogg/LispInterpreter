package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.program.IParsable;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;

public class ListNode implements IExpressionChild{
	
	private ExpressionNode expressionNode;
	private ListNode listNode;
	private boolean isEmpty;

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
	public String toString(){
		return isEmpty ? "NIL" : "(" + expressionNode.toString() + " . " +  listNode.toString() + ")";
	}

	@Override
	public IExpressionChild newInstance(){
		return new ListNode();
	}
}
