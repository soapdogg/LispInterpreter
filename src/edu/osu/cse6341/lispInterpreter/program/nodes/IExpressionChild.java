package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.program.IParsable;
import edu.osu.cse6341.lispInterpreter.program.IEvaluatable;

public interface IExpressionChild extends IParsable, IEvaluatable{

	IExpressionChild newInstance();
}
