package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;

public class ListNode implements IListNode{
	
	private IExpressionNode expressionNode;
	private IListNode listNode;
	private boolean isEmpty;

	@Override
	public void parse(){
		isEmpty = Tokenizer.getTokenizer().getCurrent().getTokenKind() == TokenKind.CLOSE_TOKEN;
		if(isEmpty) return;
//		Tokenizer.getTokenizer().getNextToken();
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
