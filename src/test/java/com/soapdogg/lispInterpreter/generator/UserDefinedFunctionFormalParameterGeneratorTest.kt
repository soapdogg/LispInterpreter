package com.soapdogg.lispInterpreter.generator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.valueretriver.AtomicValueRetriever
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class UserDefinedFunctionFormalParameterGeneratorTest {

    private val parameterCounter = 1
    private val variableNameToValueMap: Map<String, Node> = mapOf()
    private val listValueRetriever = Mockito.mock(ListValueRetriever::class.java)
    private val atomicValueRetriever = Mockito.mock(AtomicValueRetriever::class.java)
    private val userDefinedFunctionFormalParameterGenerator = UserDefinedFunctionFormalParameterGenerator(
        listValueRetriever,
        atomicValueRetriever
    )

    @Test
    fun formalParameterIsAtomNodeTest() {
        val formalParametersNode = Mockito.mock(AtomNode::class.java)
        val actual = userDefinedFunctionFormalParameterGenerator.getFormalParameters(
            formalParametersNode,
            parameterCounter,
            variableNameToValueMap
        )
        Assertions.assertTrue(actual.isEmpty())
        Mockito.verifyNoInteractions(listValueRetriever)
        Mockito.verifyNoInteractions(atomicValueRetriever)
    }

    @Test
    fun formalParameterIsExpressionNodeTest() {
        val formalParametersNode = Mockito.mock(ExpressionNode::class.java)

        val formalParametersExpressionNode = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                formalParametersNode,
                FunctionNameConstants.DEFUN,
                variableNameToValueMap
            )
        ).thenReturn(formalParametersExpressionNode)

        val formalNode = Mockito.mock(Node::class.java)
        Mockito.`when`(formalParametersExpressionNode.address).thenReturn(formalNode)
        val formalId = "formalId"
        Mockito.`when`(
            atomicValueRetriever.retrieveAtomicValue(
                formalNode,
                parameterCounter,
                FunctionNameConstants.DEFUN
            )
        ).thenReturn(formalId)

        val data = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(formalParametersExpressionNode.data).thenReturn(data)

        val actual = userDefinedFunctionFormalParameterGenerator.getFormalParameters(
            formalParametersNode,
            parameterCounter,
            variableNameToValueMap
        )
        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(formalId, actual[0])
    }
}