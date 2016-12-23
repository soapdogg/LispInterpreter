package edu.osu.cse6341.lispInterpreter.program.nodes;

/**
 * Node that represents the start node in the BNF for lisp
 */
public class StartNode implements IStartNode
{
    private final IExpressionNode expressionNode;     //child declaration sequence node

    /**
     * Default Constructor
     */
    public StartNode()
    {
        expressionNode = new ExpressionNode();
    }

    /**
     * Parses the given instance of the class that corresponds to part of the program.
     */
    @Override
    public void parse()
    {
        expressionNode.parse();
    }

    /**
     * Prints the instance of the class in a pretty fashion.
     * @return
     *      a string that represents a pretty version of the string.
     */
    @Override
    public String toString()
    {
		return "SHIT";
    }
}
