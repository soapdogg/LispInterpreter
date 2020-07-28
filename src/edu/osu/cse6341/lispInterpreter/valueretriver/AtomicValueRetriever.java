package edu.osu.cse6341.lispInterpreter.valueretriver;

import edu.osu.cse6341.lispInterpreter.exceptions.NotAtomicException;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class AtomicValueRetriever {

    public String retrieveAtomicValue(
        final LispNode node,
        final int position,
        final String functionName
    ) throws Exception{
        if(node.isNodeList()) {
            String sb = "Error! Parameter at position: " + position +
                " of function " +
                functionName +
                " is not atomic!    Actual: " +
                node.getListNotationToString(true) +
                '\n';
            throw new NotAtomicException(sb);
        }
        return node.getNodeValue();
    }
}
