package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;
import edu.osu.cse6341.lispInterpreter.program.ExpressionKind;

public interface IFunction{

	boolean isDefinedCorrectly();

	String evaluate();

	IFunction newInstance(ListNode node);

	ExpressionKind getExpressionKind();
}
