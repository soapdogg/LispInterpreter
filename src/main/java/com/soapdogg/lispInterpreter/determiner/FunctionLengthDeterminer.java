package com.soapdogg.lispInterpreter.determiner;

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants;
import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
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
