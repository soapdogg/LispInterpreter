package com.soapdogg.lispInterpreter.asserter

import com.soapdogg.lispInterpreter.comparator.NodeValueComparator
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer
import com.soapdogg.lispInterpreter.exceptions.NotAtomicException

class AtomRootNodeAsserter(
    private val numericStringDeterminer: NumericStringDeterminer,
    private val nodeValueComparator: NodeValueComparator
) {

    fun assertAtomRootNode(
        atomNode: AtomNode
    ) {
        val isNotNumeric = !numericStringDeterminer.isStringNumeric(atomNode.value)
        val isNotT = !nodeValueComparator.equalsT(atomNode.value)
        val isNotNil = !nodeValueComparator.equalsNil(atomNode.value)
        if (isNotNumeric && isNotT && isNotNil) {
            throw NotAtomicException("""Error! ${atomNode.value} is not a valid atomic value!${'\n'}""")
        }
    }
}