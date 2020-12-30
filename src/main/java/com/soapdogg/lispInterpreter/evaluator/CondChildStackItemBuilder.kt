package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.MyStack
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import java.util.*

class CondChildStackItemBuilder(
    private val nodeGenerator: NodeGenerator,
    private val topProgramStackItemCreator: TopProgramStackItemCreator
) {

    fun buildCondChildStackItems(
        condProgramStackItem: ProgramStackItem,
        programStack: MyStack<ProgramStackItem>
    ) {
        val variableMap = condProgramStackItem.variableMap
        val condChildren = condProgramStackItem.functionExpressionNode.children
        val condChildAtomNode = nodeGenerator.generateAtomNode(FunctionNameConstants.CONDCHILD)
        for (i in condChildren.size - 2 downTo 1) {
            val condChildsChildren = listOf(condChildAtomNode) +  (condChildren[i] as ExpressionListNode).children
            val condChildExpressionListNode = nodeGenerator.generateExpressionListNode(condChildsChildren)
            topProgramStackItemCreator.createTopProgramStackItem(
                condChildExpressionListNode,
                variableMap,
                programStack
            )
        }
    }
}