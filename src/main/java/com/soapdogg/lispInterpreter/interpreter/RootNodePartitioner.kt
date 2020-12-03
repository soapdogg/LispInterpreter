package com.soapdogg.lispInterpreter.interpreter

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.converter.NodeConverter
import com.soapdogg.lispInterpreter.datamodels.*

class RootNodePartitioner(
    private val nodeConverter: NodeConverter
) {
    fun partitionRootNodes(
        rootNodes: List<NodeV2>
    ): PartitionedRootNodes {
        val (defun, executables) = rootNodes.partition {
            rootNode -> if (rootNode is ExpressionListNode) {
                val rootNodeAddress = rootNode.children[0]
                if (rootNodeAddress is AtomNode) {
                    return@partition rootNodeAddress.value == FunctionNameConstants.DEFUN
                }
            }
            false
        }

        return PartitionedRootNodes(
            defun.map { nodeConverter.convertNodeV2ToNode(it) },
            executables.map { nodeConverter.convertNodeV2ToNode(it) }
        )
    }
}