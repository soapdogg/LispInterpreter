package edu.osu.cse6341.lispInterpreter.program;

import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;

public interface IEvaluatable{
	LispNode evaluate(boolean areLiteralsAllowed) throws Exception;
}
