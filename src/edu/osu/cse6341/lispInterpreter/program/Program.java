package edu.osu.cse6341.lispInterpreter.program;

import edu.osu.cse6341.lispInterpreter.program.nodes.IRootNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.StartNode;

/**
 * Class that represents a lisp Program
 */
public class Program implements IProgram
{
    private static IProgram singletonProgram;       //singleton instance of the lisp program
    private final IRootNode rootNode;               //the root node of the parse tree associated with the program
    private boolean hasEncounteredError;            //true iff the program has encountered an error while executing.

    /**
     * Default Constructor
     */
    private Program()
    {
        rootNode = new StartNode();
        hasEncounteredError = false;
    }

    /**
     * @return
     *      the singleton instance of the program
     */
    public static IProgram getProgram()
    {
        if(singletonProgram == null) singletonProgram = new Program();
        return singletonProgram;
    }

    /**
     * interprets the program
     */
    @Override
    public void interpret()
    {
        rootNode.parse();
        System.out.print(toString());
    }

    
      /**
     * @return
     *      true iff the program has encountered an error, false otherwise
     */
    @Override
    public boolean hasEncounteredAnError()
    {
        return hasEncounteredError;
    }

    /**
     * @return
     *      a string representation of the program
     */
    @Override
    public String toString()
    {
        return rootNode.toString(); 
    }
  }
