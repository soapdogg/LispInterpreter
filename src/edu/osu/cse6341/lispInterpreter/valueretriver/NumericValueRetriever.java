package edu.osu.cse6341.lispInterpreter.valueretriver;

import edu.osu.cse6341.lispInterpreter.exceptions.NotNumericException;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class NumericValueRetriever {

    public int retrieveNumericValue(
        final Node node,
        final int position,
        final String functionName
    ) throws Exception{
        if(!node.isNumeric()) {
            String sb = "Error! Parameter at position: " + position +
                " of function " +
                functionName +
                " is not numeric!    Actual: " +
                node.getListNotationToString(true) +
                '\n';
            throw new NotNumericException(sb);
        }
        return Integer.parseInt(node.getValue());
    }
}
