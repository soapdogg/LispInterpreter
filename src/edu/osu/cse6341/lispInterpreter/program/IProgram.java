package edu.osu.cse6341.lispInterpreter.program;

/**
 * Interface that defines what all a Core Program can do
 */
public interface IProgram extends IParsable
{
	/**
     * @return
     *      true iff the program has encountered an error, false otherwise
     */
    boolean hasEncounteredAnError();
}
