package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.datamodels.Stack
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class RootNodeEvaluatorTest {

    private val topProgramStackItemCreator = Mockito.mock(TopProgramStackItemCreator::class.java)
    private val condFunctionEvaluator = Mockito.mock(CondFunctionEvaluator::class.java)
    private val condChildFunctionEvaluator = Mockito.mock(CondChildFunctionEvaluator::class.java)
    private val quoteFunctionEvaluator = Mockito.mock(QuoteFunctionEvaluator::class.java)
    private val unfinishedProgramStackItemEvaluator = Mockito.mock(UnfinishedProgramStackItemEvaluator::class.java)
    private val finishedProgramStackItemEvaluator = Mockito.mock(FinishedProgramStackItemEvaluator::class.java)

    private val rootNodeEvaluator = RootNodeEvaluator(
        topProgramStackItemCreator,
        condFunctionEvaluator,
        condChildFunctionEvaluator,
        quoteFunctionEvaluator,
        unfinishedProgramStackItemEvaluator,
        finishedProgramStackItemEvaluator
    )

    @Test
    fun evaluateCondFunctionTest() {
        val rootNode = Mockito.mock(ExpressionListNode::class.java)
        val userDefinedFunctions = emptyMap<String, UserDefinedFunction>()
        val programStack = Stack<ProgramStackItem>()
        val evalStack = Stack<NodeV2>()

        val rootProgramStackItem = Mockito.mock(ProgramStackItem::class.java)
        Mockito.`when`(
            topProgramStackItemCreator.createTopProgramStackItem(
                rootNode,
                emptyMap(),
                programStack
            )
        ).then {
            programStack.push(rootProgramStackItem)
        }

        val functionName = FunctionNameConstants.COND
        Mockito.`when`(rootProgramStackItem.functionName).thenReturn(functionName)

        val result = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(
            condFunctionEvaluator.evaluateCondProgramStackItem(
                rootProgramStackItem,
                programStack
            )
        ).then {
            evalStack.push(result)
        }

        val actual = rootNodeEvaluator.evaluate(
            rootNode,
            userDefinedFunctions,
            programStack,
            evalStack
        )

        Assertions.assertEquals(result, actual)

        Mockito.verifyNoInteractions(condChildFunctionEvaluator)
        Mockito.verifyNoInteractions(quoteFunctionEvaluator)
        Mockito.verifyNoInteractions(unfinishedProgramStackItemEvaluator)
        Mockito.verifyNoInteractions(finishedProgramStackItemEvaluator)
    }

    @Test
    fun evaluateCondChildFunctionTest() {
        val rootNode = Mockito.mock(ExpressionListNode::class.java)
        val userDefinedFunctions = emptyMap<String, UserDefinedFunction>()
        val programStack = Stack<ProgramStackItem>()
        val evalStack = Stack<NodeV2>()

        val rootProgramStackItem = Mockito.mock(ProgramStackItem::class.java)
        Mockito.`when`(
            topProgramStackItemCreator.createTopProgramStackItem(
                rootNode,
                emptyMap(),
                programStack
            )
        ).then {
            programStack.push(rootProgramStackItem)
        }

        val functionName = FunctionNameConstants.CONDCHILD
        Mockito.`when`(rootProgramStackItem.functionName).thenReturn(functionName)

        val result = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(
            condChildFunctionEvaluator.evaluateCondChildFunction(
                rootProgramStackItem,
                evalStack,
                programStack
            )
        ).then {
            evalStack.push(result)
        }

        val actual = rootNodeEvaluator.evaluate(
            rootNode,
            userDefinedFunctions,
            programStack,
            evalStack
        )

        Assertions.assertEquals(result, actual)

        Mockito.verifyNoInteractions(condFunctionEvaluator)
        Mockito.verifyNoInteractions(quoteFunctionEvaluator)
        Mockito.verifyNoInteractions(unfinishedProgramStackItemEvaluator)
        Mockito.verifyNoInteractions(finishedProgramStackItemEvaluator)
    }

    @Test
    fun evaluateQuoteFunctionTest() {
        val rootNode = Mockito.mock(ExpressionListNode::class.java)
        val userDefinedFunctions = emptyMap<String, UserDefinedFunction>()
        val programStack = Stack<ProgramStackItem>()
        val evalStack = Stack<NodeV2>()

        val rootProgramStackItem = Mockito.mock(ProgramStackItem::class.java)
        Mockito.`when`(
            topProgramStackItemCreator.createTopProgramStackItem(
                rootNode,
                emptyMap(),
                programStack
            )
        ).then {
            programStack.push(rootProgramStackItem)
        }

        val functionName = FunctionNameConstants.QUOTE
        Mockito.`when`(rootProgramStackItem.functionName).thenReturn(functionName)

        val result = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(
            quoteFunctionEvaluator.evaluateQuoteFunction(
                rootProgramStackItem,
                evalStack,
                programStack
            )
        ).then {
            evalStack.push(result)
        }

        val actual = rootNodeEvaluator.evaluate(
            rootNode,
            userDefinedFunctions,
            programStack,
            evalStack
        )

        Assertions.assertEquals(result, actual)

        Mockito.verifyNoInteractions(condFunctionEvaluator)
        Mockito.verifyNoInteractions(condChildFunctionEvaluator)
        Mockito.verifyNoInteractions(unfinishedProgramStackItemEvaluator)
        Mockito.verifyNoInteractions(finishedProgramStackItemEvaluator)
    }

    @Test
    fun evaluateUnfinishedProgramStackItemTest() {
        val rootNode = Mockito.mock(ExpressionListNode::class.java)
        val userDefinedFunctions = emptyMap<String, UserDefinedFunction>()
        val programStack = Stack<ProgramStackItem>()
        val evalStack = Stack<NodeV2>()

        val rootProgramStackItem = Mockito.mock(ProgramStackItem::class.java)
        Mockito.`when`(
            topProgramStackItemCreator.createTopProgramStackItem(
                rootNode,
                emptyMap(),
                programStack
            )
        ).then {
            programStack.push(rootProgramStackItem)
        }

        val functionName = FunctionNameConstants.LESS
        Mockito.`when`(rootProgramStackItem.functionName).thenReturn(functionName)

        val currentParameterIndex = 0
        Mockito.`when`(rootProgramStackItem.currentParameterIndex).thenReturn(currentParameterIndex)

        val functionExpressionListNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(rootProgramStackItem.functionExpressionNode).thenReturn(functionExpressionListNode)

        val child0 = Mockito.mock(NodeV2::class.java)
        val child1 = Mockito.mock(NodeV2::class.java)
        val children = listOf(child0, child1)
        Mockito.`when`(functionExpressionListNode.children).thenReturn(children)

        val result = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(
            unfinishedProgramStackItemEvaluator.evaluateUnfinishedProgramStackItem(
                rootProgramStackItem,
                evalStack,
                programStack
            )
        ).then {
            evalStack.push(result)
        }

        val actual = rootNodeEvaluator.evaluate(
            rootNode,
            userDefinedFunctions,
            programStack,
            evalStack
        )

        Assertions.assertEquals(result, actual)

        Mockito.verifyNoInteractions(condFunctionEvaluator)
        Mockito.verifyNoInteractions(condChildFunctionEvaluator)
        Mockito.verifyNoInteractions(quoteFunctionEvaluator)
        Mockito.verifyNoInteractions(finishedProgramStackItemEvaluator)
    }

    @Test
    fun evaluateFinishedProgramStackItemTest() {
        val rootNode = Mockito.mock(ExpressionListNode::class.java)
        val userDefinedFunctions = emptyMap<String, UserDefinedFunction>()
        val programStack = Stack<ProgramStackItem>()
        val evalStack = Stack<NodeV2>()

        val rootProgramStackItem = Mockito.mock(ProgramStackItem::class.java)
        Mockito.`when`(
            topProgramStackItemCreator.createTopProgramStackItem(
                rootNode,
                emptyMap(),
                programStack
            )
        ).then {
            programStack.push(rootProgramStackItem)
        }

        val functionName = FunctionNameConstants.LESS
        Mockito.`when`(rootProgramStackItem.functionName).thenReturn(functionName)

        val currentParameterIndex = 1
        Mockito.`when`(rootProgramStackItem.currentParameterIndex).thenReturn(currentParameterIndex)

        val functionExpressionListNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(rootProgramStackItem.functionExpressionNode).thenReturn(functionExpressionListNode)

        val child0 = Mockito.mock(NodeV2::class.java)
        val child1 = Mockito.mock(NodeV2::class.java)
        val children = listOf(child0, child1)
        Mockito.`when`(functionExpressionListNode.children).thenReturn(children)

        val result = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(
            finishedProgramStackItemEvaluator.evaluateFinishedProgramStackItem(
                rootProgramStackItem,
                userDefinedFunctions,
                evalStack,
                programStack
            )
        ).then {
            evalStack.push(result)
        }

        val actual = rootNodeEvaluator.evaluate(
            rootNode,
            userDefinedFunctions,
            programStack,
            evalStack
        )

        Assertions.assertEquals(result, actual)

        Mockito.verifyNoInteractions(condFunctionEvaluator)
        Mockito.verifyNoInteractions(condChildFunctionEvaluator)
        Mockito.verifyNoInteractions(quoteFunctionEvaluator)
        Mockito.verifyNoInteractions(unfinishedProgramStackItemEvaluator)
    }
}