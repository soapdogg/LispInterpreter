package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.NumericValueRetriever
import java.util.*

class StackEvaluator (
    private val nodeGenerator: NodeGenerator,
    private val numericStringDeterminer: NumericStringDeterminer
){

    fun evaluate(
        stack: Stack<AtomNode>
    ): Stack<AtomNode> {
        val programStack = Stack<AtomNode>()
        while(stack.isNotEmpty()) {
            val head = stack.pop()

            val value = head.value
            if (value == FunctionNameConstants.ATOM) {
                val first = programStack.pop()
                if (programStack.peek().value == ReservedValuesConstants.NIL) {
                    programStack.pop()
                    val resultingNode = nodeGenerator.generateAtomNode(true)
                    programStack.push(resultingNode)
                } else {
                    while (programStack.peek().value != ReservedValuesConstants.NIL) {
                        programStack.pop()
                    }
                    programStack.pop()
                    val resultingNode = nodeGenerator.generateAtomNode(false)
                    programStack.push(resultingNode)
                }
            }
            else if (value == FunctionNameConstants.INT) {
                val first = programStack.pop()
                if (programStack.peek().value == ReservedValuesConstants.NIL) {
                    programStack.pop()
                    val isFirstNumeric = numericStringDeterminer.isStringNumeric(first.value)
                    val resultingNode = nodeGenerator.generateAtomNode(isFirstNumeric)
                    programStack.push(resultingNode)
                } else {
                    while (programStack.peek().value != ReservedValuesConstants.NIL) {
                        programStack.pop()
                    }
                    programStack.pop()
                    val resultingNode = nodeGenerator.generateAtomNode(false)
                    programStack.push(resultingNode)
                }
            }
            else if (value == FunctionNameConstants.NULL) {
                val first = programStack.pop()
                if (programStack.peek().value == ReservedValuesConstants.NIL) {
                    programStack.pop()
                    val isNil = first.value == ReservedValuesConstants.NIL
                    val resultingNode = nodeGenerator.generateAtomNode(isNil)
                    programStack.push(resultingNode)
                } else {
                    while (programStack.peek().value != ReservedValuesConstants.NIL) {
                        programStack.pop()
                    }
                    programStack.pop()
                    val resultingNode = nodeGenerator.generateAtomNode(false)
                    programStack.push(resultingNode)
                }
            }
            else if (value == FunctionNameConstants.QUOTE) {
                val tempStack = Stack<AtomNode>()
                val first = programStack.pop()
                tempStack.push(first)
                while (programStack.peek().value != ReservedValuesConstants.NIL) {
                    val t = programStack.pop()
                    tempStack.push(t)
                }
                val nil = programStack.pop()
                while(tempStack.isNotEmpty()) {
                    val t = tempStack.pop()
                    programStack.push(t)
                }
            }
            else if (value == FunctionNameConstants.EQ) {
                val first = programStack.pop()
                val second = programStack.pop()
                val nil = programStack.pop()
                val result = first.value == second.value
                val resultingNode = nodeGenerator.generateAtomNode(result)
                programStack.push(resultingNode)
            } else if (value == FunctionNameConstants.GREATER) {
                val first = programStack.pop()
                val second = programStack.pop()
                val nil = programStack.pop()
                val result = first.value.toInt() > second.value.toInt()
                val resultingNode = nodeGenerator.generateAtomNode(result)
                programStack.push(resultingNode)
            } else if (value == FunctionNameConstants.LESS) {
                val first = programStack.pop()
                val second = programStack.pop()
                val result = first.value.toInt() < second.value.toInt()
                val resultingNode = nodeGenerator.generateAtomNode(result)
                programStack.push(resultingNode)
            } else if (value == FunctionNameConstants.MINUS) {
                val first = programStack.pop()
                val second = programStack.pop()
                val nil = programStack.pop()
                val result = first.value.toInt() - second.value.toInt()
                val resultingNode = nodeGenerator.generateAtomNode(result)
                programStack.push(resultingNode)
            } else if (value == FunctionNameConstants.PLUS) {
                val first = programStack.pop()
                val second = programStack.pop()
                val nil = programStack.pop()
                val result = first.value.toInt() + second.value.toInt()
                val resultingNode = nodeGenerator.generateAtomNode(result)
                programStack.push(resultingNode)
            } else if (value == FunctionNameConstants.TIMES) {
                val first = programStack.pop()
                val second = programStack.pop()
                val nil = programStack.pop()
                val result = first.value.toInt() * second.value.toInt()
                val resultingNode = nodeGenerator.generateAtomNode(result)
                programStack.push(resultingNode)
            }
            else {
                programStack.push(head)
            }
        }
        programStack.reverse()
        return programStack
    }
}