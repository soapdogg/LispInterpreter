package edu.osu.cse6341.lispInterpreter.program;

import edu.osu.cse6341.lispInterpreter.program.nodes.StartNode;

/**
 * Class that represents a lisp Program
 */
public class Program{
    private static Program singletonProgram;       //singleton instance of the lisp program
    private final StartNode startNode;               //the root node of the parse tree associated with the program
    private boolean hasEncounteredError;            //true iff the program has encountered an error while executing.

    /**
     * Default Constructor
     */
    private Program()
    {
        startNode = new StartNode();
        hasEncounteredError = false;
    }

    /**
     * @return
     *      the singleton instance of the program
     */
    public static Program getProgram()
    {
        if(singletonProgram == null) singletonProgram = new Program();
        return singletonProgram;
    }

    /**
     * interprets the program
     */
    public void parse()
    {
        startNode.parse();
    }

    
      /**
     * @return
     *      true iff the program has encountered an error, false otherwise
     */
    public boolean hasEncounteredAnError()
    {
        return hasEncounteredError;
    }

    /**
     * @return
     *      a string representation of the program
     */
    public String toString()
    {
        return startNode.toString(); 
    }
  }
