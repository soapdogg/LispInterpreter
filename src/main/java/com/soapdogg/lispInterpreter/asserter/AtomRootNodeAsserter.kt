package com.soapdogg.lispInterpreter.asserter

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer
import com.soapdogg.lispInterpreter.exceptions.NotAtomicException

class AtomRootNodeAsserter(
    private val numericStringDeterminer: NumericStringDeterminer
) {

    fun assertAtomRootNode(
        atomNode: AtomNode
    ) {
        val isNotNumeric = !numericStringDeterminer.isStringNumeric(atomNode.value)
        val isNotT = atomNode.value != ReservedValuesConstants.T
        val isNotNil = atomNode.value != ReservedValuesConstants.NIL
        if (isNotNumeric && isNotT && isNotNil) {
            throw NotAtomicException("""Error! ${atomNode.value} is not a valid atomic value!${'\n'}""")
        }
    }
}