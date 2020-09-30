package com.soapdogg.lispInterpreter.asserter;

import com.soapdogg.lispInterpreter.comparator.NodeValueComparator;
import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer;
import com.soapdogg.lispInterpreter.exceptions.NotAtomicException;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class AtomRootNodeAsserter {

    private final NumericStringDeterminer numericStringDeterminer;
    private final NodeValueComparator nodeValueComparator;

    public void assertAtomRootNode(
        final AtomNode atomNode
    ) throws NotAtomicException {
        boolean isNotNumeric = !numericStringDeterminer.isStringNumeric(atomNode.getValue());
        boolean isNotT = !nodeValueComparator.equalsT(atomNode.getValue());
        boolean isNotNil = !nodeValueComparator.equalsNil(atomNode.getValue());
        if (isNotNumeric && isNotT && isNotNil){
            throw new NotAtomicException("Error! " + atomNode.getValue() + " is not a valid atomic value!\n");
        }
    }
}
