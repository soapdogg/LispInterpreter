package com.soapdogg.lispInterpreter.generator

import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.functions.DefunFunction
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class UserDefinedFunctionGeneratorTest {

    private val defunNode = Mockito.mock(ExpressionNode::class.java)
    private val defunNodes: List<Node> = listOf(defunNode)
    private val defunNodeData = Mockito.mock(Node::class.java)
    private val defunFunction = Mockito.mock(DefunFunction::class.java)
    private val userDefinedFunction = Mockito.mock(UserDefinedFunction::class.java)
    private val userDefinedFunctionGenerator = UserDefinedFunctionGenerator(
        defunFunction
    )

    @Test
    fun generateUserDefinedFunctionsTest() {
        Mockito.`when`(defunNode.data).thenReturn(defunNodeData)
        Mockito.`when`(defunFunction.evaluateLispFunction(defunNodeData)).thenReturn(userDefinedFunction)

        val actual = userDefinedFunctionGenerator.generateUserDefinedFunctions(
                defunNodes
        )
        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(userDefinedFunction, actual[0])
    }
}