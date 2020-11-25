package com.soapdogg.lispInterpreter.printer

import com.soapdogg.lispInterpreter.datamodels.AtomNode

class AtomNodePrinter {
    fun printAtomNode(
        atomNode: AtomNode
    ): String {
        return atomNode.value
    }
}