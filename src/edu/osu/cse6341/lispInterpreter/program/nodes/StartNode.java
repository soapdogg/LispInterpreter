package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.program.IParsable;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.EndOfFileToken;
/**
 * Node that represents the start node in the BNF for lisp
 */
public class StartNode implements IParsable{
    private final ExpressionNode expressionNode;
	private StartNode startNode;

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
		if(Tokenizer.getTokenizer().getCurrent() instanceof EndOfFileToken) return;
		startNode = new StartNode();
		startNode.parse();
    }

    /**
     * Prints the instance of the class in a pretty fashion.
     * @return
     *      a string that represents a pretty version of the string.
     */
    @Override
    public String toString()
    {
		StringBuilder sb = new StringBuilder();
		sb.append(expressionNode.toString());
		sb.append('\n');
		if(startNode != null) sb.append(startNode.toString());
		return sb.toString();
    }
}
