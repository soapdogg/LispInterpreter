package com.soapdogg.lispInterpreter.generator

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.asserter.UserDefinedFormalParametersAsserter
import com.soapdogg.lispInterpreter.asserter.UserDefinedFunctionNameAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class UserDefinedFunctionGeneratorTest {
    private val params = Mockito.mock(ExpressionListNode::class.java)

    private val functionLengthAsserter = Mockito.mock(FunctionLengthAsserter::class.java)
    private val userDefinedFunctionNameAsserter = Mockito.mock(UserDefinedFunctionNameAsserter::class.java)
    private val userDefinedFormalParametersAsserter = Mockito.mock(UserDefinedFormalParametersAsserter::class.java)

    private val defunFunction = UserDefinedFunctionGenerator(
        functionLengthAsserter,
        userDefinedFunctionNameAsserter,
        userDefinedFormalParametersAsserter
    )

    @Test
    fun defunFunctionEmptyFormalParamsTest() {
        val functionNameNode = Mockito.mock(AtomNode::class.java)
        val functionName = "functionName"
        Mockito.`when`(functionNameNode.value).thenReturn(functionName)

        val formalParametersNodeV2 = Mockito.mock(ExpressionListNode::class.java)
        val formalParameters = emptyList<String>()
        Mockito.`when`(formalParametersNodeV2.children).thenReturn(listOf())

        val bodyV2 = Mockito.mock(NodeV2::class.java)

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
        Assertions.assertEquals(bodyV2, body1)
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

    @Test
    fun defunFunctionNonEmptyFormalParamsTest() {
        val functionNameNode = Mockito.mock(AtomNode::class.java)
        val functionName = "functionName"
        Mockito.`when`(functionNameNode.value).thenReturn(functionName)

        val formalParameterChild0 = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(formalParameterChild0.value).thenReturn(ReservedValuesConstants.T)
        val formalParameterChild1 = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(formalParameterChild1.value).thenReturn(FunctionNameConstants.QUOTE)

        val formalParametersChildren = listOf(
            formalParameterChild0,
            formalParameterChild1
        )

        val formalParametersNodeV2 = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(formalParametersNodeV2.children).thenReturn(formalParametersChildren)

        val formalParameters = listOf(
            ReservedValuesConstants.T
        )

        val bodyV2 = Mockito.mock(NodeV2::class.java)

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
        Assertions.assertEquals(bodyV2, body1)
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

    @Test
    fun defunFunctionFormalParamsAtomTest() {
        val functionNameNode = Mockito.mock(AtomNode::class.java)
        val functionName = "functionName"
        Mockito.`when`(functionNameNode.value).thenReturn(functionName)

        val formalParametersNodeV2 = Mockito.mock(AtomNode::class.java)
        val formalParameters = listOf<String>()

        val bodyV2 = Mockito.mock(NodeV2::class.java)

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
        Assertions.assertEquals(bodyV2, body1)
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