package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.asserter.UserDefinedFormalParametersAsserter
import com.soapdogg.lispInterpreter.asserter.UserDefinedFunctionNameAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.converter.NodeConverter
import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.functions.DefunFunction
import com.soapdogg.lispInterpreter.generator.UserDefinedFunctionFormalParameterGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class DefunFunctionTest {
    private val params = Mockito.mock(ExpressionListNode::class.java)

    private val functionLengthAsserter = Mockito.mock(FunctionLengthAsserter::class.java)
    private val userDefinedFunctionNameAsserter = Mockito.mock(UserDefinedFunctionNameAsserter::class.java)
    private val userDefinedFunctionFormalParameterGenerator = Mockito.mock(UserDefinedFunctionFormalParameterGenerator::class.java)
    private val userDefinedFormalParametersAsserter = Mockito.mock(UserDefinedFormalParametersAsserter::class.java)
    private val nodeConverter = Mockito.mock(NodeConverter::class.java)

    private val defunFunction = DefunFunction(
        functionLengthAsserter,
        userDefinedFunctionNameAsserter,
        userDefinedFunctionFormalParameterGenerator,
        userDefinedFormalParametersAsserter,
        nodeConverter
    )

    @Test
    fun defunFunctionTest() {
        val functionNameNode = Mockito.mock(AtomNode::class.java)
        val functionName = "functionName"
        Mockito.`when`(functionNameNode.value).thenReturn(functionName)

        val formalParametersNodeV2 = Mockito.mock(NodeV2::class.java)
        val formalParametersNode = Mockito.mock(Node::class.java)
        Mockito.`when`(nodeConverter.convertNodeV2ToNode(formalParametersNodeV2)).thenReturn(formalParametersNode)

        val formalParameters = emptyList<String>()
        Mockito.`when`(
            userDefinedFunctionFormalParameterGenerator.getFormalParameters(
                formalParametersNode,
                FunctionLengthConstants.ONE, emptyMap())
        ).thenReturn(formalParameters)

        val bodyV2 = Mockito.mock(NodeV2::class.java)
        val body = Mockito.mock(Node::class.java)
        Mockito.`when`(nodeConverter.convertNodeV2ToNode(bodyV2)).thenReturn(body)

        val defunNode = Mockito.mock(NodeV2::class.java)
        val paramsChildren = listOf(
            defunNode,
            functionNameNode,
            formalParametersNodeV2,
            bodyV2
        )
        Mockito.`when`(params.children).thenReturn(paramsChildren)

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