package edu.osu.cse6341.lispInterpreter.valueretriver;

import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.exceptions.NotAListException;
import edu.osu.cse6341.lispInterpreter.printer.DotNotationPrinter;
import edu.osu.cse6341.lispInterpreter.program.Environment;
import edu.osu.cse6341.lispInterpreter.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class ListValueRetriever {

    private final ExpressionNodeDeterminer expressionNodeDeterminer;
    private final Environment environment;
    private final DotNotationPrinter dotNotationPrinter;

    public ExpressionNode retrieveListValue(
        final LispNode node,
        final String functionName
    ) throws Exception{
        boolean isNodeAList = expressionNodeDeterminer.isExpressionNode(node);
        if (isNodeAList) {
            return (ExpressionNode)node;
        }

        String nodeValue = ((AtomNode)node).getValue();
        boolean isVariable = environment.isVariableName(nodeValue);
        if(isVariable) {
            LispNode result = environment.getVariableValue(nodeValue);
            boolean isVariableList = expressionNodeDeterminer.isExpressionNode(result);
            if (isVariableList) return (ExpressionNode) result;
        }

        String sb = "Error! Parameter of " + functionName +
            " is not a list.    Actual: " +
            dotNotationPrinter.printInDotNotation(node) +
            '\n';
        throw new NotAListException(sb);
    }
}
