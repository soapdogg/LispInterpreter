package com.soapdogg.lispInterpreter.printer;

import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class AtomNodePrinter {

    String printAtomNode(
        final AtomNode atomNode
    ) {
        return atomNode.getValue();
    }
}
