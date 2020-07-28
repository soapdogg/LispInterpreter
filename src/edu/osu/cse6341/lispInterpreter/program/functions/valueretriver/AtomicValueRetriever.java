package edu.osu.cse6341.lispInterpreter.program.functions.valueretriver;

import edu.osu.cse6341.lispInterpreter.exceptions.NotAtomicException;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;

public class AtomicValueRetriever {

    public String retrieveAtomicValue(
        Node node,
        int position,
        String functionName
    ) throws Exception{
        if(node.isList()) {
            String sb = "Error! Parameter at position: " + position +
                " of function " +
                functionName +
                " is not atomic!    Actual: " +
                node.getListNotationToString(true) +
                '\n';
            throw new NotAtomicException(sb);
        }
        return node.getValue();
    }
}
