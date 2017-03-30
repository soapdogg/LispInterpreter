package edu.osu.cse6341.lispInterpreter.program;

import edu.osu.cse6341.lispInterpreter.program.types.IType;

public interface ITypeCheckable {
    IType typeCheck(boolean areLiteralsAllowed) throws Exception;
}
