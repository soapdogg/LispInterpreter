package com.soapdogg.lispInterpreter.valueretriver;

import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer;
import com.soapdogg.lispInterpreter.exceptions.NotAtomicException;
import com.soapdogg.lispInterpreter.printer.ListNotationPrinter;
import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class AtomicValueRetriever {

    private final ExpressionNodeDeterminer expressionNodeDeterminer;
    private final ListNotationPrinter listNotationPrinter;

    public String retrieveAtomicValue(
        final Node node,
        final int position,
        final String functionName
    ) throws NotAtomicException {
        boolean isList = expressionNodeDeterminer.isExpressionNode(node);
        if(isList) {
            String listNotation = listNotationPrinter.printInListNotation(
                node
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
