package com.soapdogg.lispInterpreter.interpreter

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.PartitionedRootNodes

class RootNodePartitioner {
    fun partitionRootNodes(
        rootNodes: List<Node>
    ): PartitionedRootNodes {
        val (defun, executables) = rootNodes.partition {
            rootNode: Node -> if (rootNode is ExpressionNode) {
                val rootNodeAddress = rootNode.address
                if (rootNodeAddress is AtomNode) {
                    return@partition rootNodeAddress.value == FunctionNameConstants.DEFUN
                }
            }
            false
        }

        return PartitionedRootNodes(
            defun,
            executables
        )
    }
}