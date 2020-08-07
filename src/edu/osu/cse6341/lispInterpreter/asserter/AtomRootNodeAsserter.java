package edu.osu.cse6341.lispInterpreter.asserter;

import edu.osu.cse6341.lispInterpreter.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.datamodels.AtomNode;
import edu.osu.cse6341.lispInterpreter.determiner.NumericStringDeterminer;
import edu.osu.cse6341.lispInterpreter.exceptions.NotAtomicException;
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
