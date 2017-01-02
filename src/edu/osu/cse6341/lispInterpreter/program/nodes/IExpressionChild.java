package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.program.IParsable;
import edu.osu.cse6341.lispInterpreter.program.IEvaluatable;
import edu.osu.cse6341.lispInterpreter.program.ICanBeUndefinable;

public interface IExpressionChild extends IParsable, IEvaluatable, ICanBeUndefinable{

	IExpressionChild newInstance();
}
