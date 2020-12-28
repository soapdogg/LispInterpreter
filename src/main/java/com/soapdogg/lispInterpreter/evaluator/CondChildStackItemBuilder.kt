package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.generator.ProgramStackItemGenerator
import java.util.*

class CondChildStackItemBuilder(
    private val nodeGenerator: NodeGenerator,
    private val programStackItemGenerator: ProgramStackItemGenerator
) {

    fun buildCondChildStackItems(
        condProgramStackItem: ProgramStackItem,
        programStack: Stack<ProgramStackItem>
    ): Stack<ProgramStackItem> {
        val variableMap = condProgramStackItem.variableMap
        val condChildren = condProgramStackItem.functionExpressionNode.children
        val condChildAtomNode = nodeGenerator.generateAtomNode(FunctionNameConstants.CONDCHILD)
        for (i in condChildren.size - 2 downTo 1) {
            val condChildsChildren = listOf(condChildAtomNode) +  (condChildren[i] as ExpressionListNode).children
            val condChildExpressionListNode = nodeGenerator.generateExpressionListNode(condChildsChildren)
            val condChildStackItem = programStackItemGenerator.generateProgramStackItem(
                condChildExpressionListNode,
                1,
                variableMap
            )
            programStack.push(
                condChildStackItem
            )
        }
        return programStack
    }
}