package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.program.IParsable;
import edu.osu.cse6341.lispInterpreter.program.IEvaluatable;
import edu.osu.cse6341.lispInterpreter.program.ExpressionKind;

public interface IExpressionChild extends IParsable, IEvaluatable{
	
	ExpressionKind getExpressionKind();

	IExpressionChild newInstance();
}
