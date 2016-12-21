package edu.osu.cse6341.lispInterpreter;

public interface IInterpreter{
	public void interpret();

	public void incrementOpenCount();

	public void incrementClosingCount();

	public void incrementNumericAtomCount();

	public void addToNumericAtomSum(int atomValue);

	public void addToLiteralAtoms(String atomValue);
}
