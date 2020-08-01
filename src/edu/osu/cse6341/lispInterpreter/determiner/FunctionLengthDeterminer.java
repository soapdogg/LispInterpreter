package edu.osu.cse6341.lispInterpreter.determiner;

import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class FunctionLengthDeterminer {

    public int determineFunctionLength(LispNode node) {
        if (node instanceof AtomNode) {
            AtomNode atomNode = (AtomNode)node;
            return atomNode.getValue().equals(ReservedValuesConstants.NIL) ? 0 : 1;
        } else {
            ExpressionNode expressionNode = (ExpressionNode)node;
            return determineFunctionLength(expressionNode.getData()) + 1;
        }
    }
}
