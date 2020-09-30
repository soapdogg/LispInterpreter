package com.soapdogg.lispInterpreter.determiner;

import com.soapdogg.lispInterpreter.datamodels.ExpressionNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class ExpressionNodeDeterminer {

    public boolean isExpressionNode(final Node node) {
        return node instanceof ExpressionNode;
    }
}
