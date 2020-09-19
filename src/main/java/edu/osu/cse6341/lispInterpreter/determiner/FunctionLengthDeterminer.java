package edu.osu.cse6341.lispInterpreter.determiner;

import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.AtomNode;
import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class FunctionLengthDeterminer {

    public int determineFunctionLength(final Node node) {
        if (node instanceof AtomNode) {
            AtomNode atomNode = (AtomNode)node;
            return atomNode.getValue().equals(ReservedValuesConstants.NIL) ? 0 : 1;
        } else {
            ExpressionNode expressionNode = (ExpressionNode)node;
            return determineFunctionLength(expressionNode.getData()) + 1;
        }
    }
}
