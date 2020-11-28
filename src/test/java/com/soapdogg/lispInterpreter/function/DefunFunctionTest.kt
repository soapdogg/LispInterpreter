package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.asserter.UserDefinedFormalParametersAsserter
import com.soapdogg.lispInterpreter.asserter.UserDefinedFunctionNameAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.functions.DefunFunction
import com.soapdogg.lispInterpreter.generator.UserDefinedFunctionFormalParameterGenerator
import com.soapdogg.lispInterpreter.valueretriver.AtomicValueRetriever
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class DefunFunctionTest {
    private val params = Mockito.mock(Node::class.java)

    private val functionLengthAsserter = Mockito.mock(FunctionLengthAsserter::class.java)
    private val atomicValueRetriever = Mockito.mock(AtomicValueRetriever::class.java)
    private val listValueRetriever = Mockito.mock(ListValueRetriever::class.java)
    private val userDefinedFunctionNameAsserter = Mockito.mock(UserDefinedFunctionNameAsserter::class.java)
    private val userDefinedFunctionFormalParameterGenerator = Mockito.mock(UserDefinedFunctionFormalParameterGenerator::class.java)
    private val userDefinedFormalParametersAsserter = Mockito.mock(UserDefinedFormalParametersAsserter::class.java)

    private val defunFunction = DefunFunction(
        functionLengthAsserter,
        atomicValueRetriever,
        listValueRetriever,
        userDefinedFunctionNameAsserter,
        userDefinedFunctionFormalParameterGenerator,
        userDefinedFormalParametersAsserter
    )

    @Test
    fun defunFunctionTest() {
        val functionNameNode = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                params,
                FunctionNameConstants.DEFUN, emptyMap())
        ).thenReturn(functionNameNode)

        val functionNameNodeAddress = Mockito.mock(Node::class.java)
        Mockito.`when`(functionNameNode.address).thenReturn(functionNameNodeAddress)

        val functionName = "functionName"
        Mockito.`when`(
            atomicValueRetriever.retrieveAtomicValue(
                functionNameNodeAddress,
                FunctionLengthConstants.ONE,
                FunctionNameConstants.DEFUN
            )
        ).thenReturn(functionName)

        val functionNameNodeData = Mockito.mock(Node::class.java)
        Mockito.`when`(functionNameNode.data).thenReturn(functionNameNodeData)

        val tempNode = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                functionNameNodeData,
                FunctionNameConstants.DEFUN, emptyMap())
        ).thenReturn(tempNode)

        val tempNodeAddress = Mockito.mock(Node::class.java)
        Mockito.`when`(tempNode.address).thenReturn(tempNodeAddress)

        val formalParameters = emptyList<String>()
        Mockito.`when`(
            userDefinedFunctionFormalParameterGenerator.getFormalParameters(
                tempNodeAddress,
                FunctionLengthConstants.ONE, emptyMap())
        ).thenReturn(formalParameters)

        val body = Mockito.mock(Node::class.java)
        Mockito.`when`(tempNode.data).thenReturn(body)

        val (formalParameters1, body1, functionName1) = defunFunction.evaluateLispFunction(params)
        Assertions.assertEquals(formalParameters, formalParameters1)
        Assertions.assertEquals(body, body1)
        Assertions.assertEquals(functionName, functionName1)
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.DEFUN,
            FunctionLengthConstants.FOUR,
            params
        )
        Mockito.verify(userDefinedFunctionNameAsserter).assertFunctionNameIsValid(functionName)
        Mockito.verify(userDefinedFormalParametersAsserter).assertFormalParameters(
            formalParameters
        )
    }
}