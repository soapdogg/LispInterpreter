package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.asserter.CondFunctionParameterAsserter
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.evaluator.CondFunctionEvaluator
import com.soapdogg.lispInterpreter.functions.CondFunction
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class CondFunctionTest {
    private val params = Mockito.mock(Node::class.java)
    private val userDefinedFunctions: List<UserDefinedFunction> = emptyList()
    private val variableNameToValueMap: Map<String, Node> = emptyMap()

    private val condFunctionParameterAsserter = Mockito.mock(CondFunctionParameterAsserter::class.java)
    private val condFunctionEvaluator = Mockito.mock(CondFunctionEvaluator::class.java)

    private val condFunction = CondFunction(
        condFunctionParameterAsserter,
        condFunctionEvaluator
    )

    @Test
    fun evaluateLispFunctionTest() {
        val expected = Mockito.mock(Node::class.java)
        Mockito.`when`(
            condFunctionEvaluator.evaluateCondFunction(
                params,
                userDefinedFunctions,
                variableNameToValueMap
            )
        ).thenReturn(expected)

        val actual = condFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        )
        Assertions.assertEquals(expected, actual)
        Mockito.verify(condFunctionParameterAsserter).assertCondFunctionParameters(
            params,
            variableNameToValueMap
        )
    }
}