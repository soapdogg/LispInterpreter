package edu.osu.cse6341.lispInterpreter.valueretriver;

import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.determiner.FunctionLengthDeterminer;
import edu.osu.cse6341.lispInterpreter.exceptions.NotAListException;
import edu.osu.cse6341.lispInterpreter.printer.DotNotationPrinter;
import edu.osu.cse6341.lispInterpreter.program.Environment;
import edu.osu.cse6341.lispInterpreter.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.singleton.EnvironmentSingleton;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class ListValueRetriever {

    private final ExpressionNodeDeterminer expressionNodeDeterminer;
    private final DotNotationPrinter dotNotationPrinter;
    private final FunctionLengthDeterminer functionLengthDeterminer;

    public ExpressionNode retrieveListValue(
        final LispNode node,
        final String functionName
    ) throws Exception{
        Environment e = EnvironmentSingleton.INSTANCE.getEnvironment();
        boolean isVariable = false;
        boolean isVariableList = false;
        String temp = "blah";
        if (!expressionNodeDeterminer.isExpressionNode(node)){
            temp = ((AtomNode)node).getValue();
            isVariable = e.isVariableName(temp);
            if(isVariable) isVariableList = expressionNodeDeterminer.isExpressionNode(e.getVariableValue(temp));
        }

        int nodeLength = functionLengthDeterminer.determineFunctionLength(node);
        if((!isVariable && !expressionNodeDeterminer.isExpressionNode(node) && nodeLength == 1) || (isVariable && !isVariableList) || (!expressionNodeDeterminer.isExpressionNode(node))) {
            String sb = "Error! Parameter of " + functionName +
                " is not a list.    Actual: " +
                dotNotationPrinter.printInDotNotation(node) +
                '\n';
            throw new NotAListException(sb);
        }
        LispNode result = isVariableList ? e.getVariableValue(temp) : node;
        return (ExpressionNode)result;
    }
}
