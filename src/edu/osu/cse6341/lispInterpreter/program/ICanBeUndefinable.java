package edu.osu.cse6341.lispInterpreter.program;

public interface ICanBeUndefinable{
	boolean isUndefined();

	boolean isLiteral();

	boolean isNumeric();
}
