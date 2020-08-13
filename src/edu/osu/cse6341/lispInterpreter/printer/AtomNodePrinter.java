package edu.osu.cse6341.lispInterpreter.printer;

import edu.osu.cse6341.lispInterpreter.datamodels.AtomNode;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class AtomNodePrinter {

    String printAtomNode(
        final AtomNode atomNode
    ) {
        return atomNode.getValue();
    }
}
