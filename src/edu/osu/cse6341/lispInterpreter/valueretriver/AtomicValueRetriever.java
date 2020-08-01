package edu.osu.cse6341.lispInterpreter.valueretriver;

import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.exceptions.NotAtomicException;
import edu.osu.cse6341.lispInterpreter.printer.ListNotationPrinter;
import edu.osu.cse6341.lispInterpreter.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class AtomicValueRetriever {

    private final ExpressionNodeDeterminer expressionNodeDeterminer;
    private final ListNotationPrinter listNotationPrinter;

    public String retrieveAtomicValue(
        final LispNode node,
        final int position,
        final String functionName
    ) throws NotAtomicException {
        boolean isList = expressionNodeDeterminer.isExpressionNode(node);
        if(isList) {
            String listNotation = listNotationPrinter.printInListNotation(
                node,
                true
            );
            String sb = "Error! Parameter at position: " + position +
                " of function " +
                functionName +
                " is not atomic!    Actual: " +
                listNotation +
                '\n';
            throw new NotAtomicException(sb);
        }
        return ((AtomNode)node).getValue();
    }
}
