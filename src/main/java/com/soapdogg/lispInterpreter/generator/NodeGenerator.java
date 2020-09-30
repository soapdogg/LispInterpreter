package com.soapdogg.lispInterpreter.generator;

import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class NodeGenerator {

    public AtomNode generateAtomNode(
        final boolean value
    ) {
        String t = value ? ReservedValuesConstants.T : ReservedValuesConstants.NIL;
        return AtomNode.newInstance(
            t
        );
    }

    public AtomNode generateAtomNode(
        final int value
    ) {
        String t = Integer.toString(value);
        return AtomNode.newInstance(
            t
        );
    }

    public AtomNode generateAtomNode(
        final String value
    ) {
        return AtomNode.newInstance(value);
    }

    public ExpressionNode generateExpressionNode(
        final Node address,
        final Node data
    ) {
        return ExpressionNode.newInstance(
            address,
            data
        );
    }
}
