package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionsConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.determiner.UserDefinedFunctionNameDeterminer
import java.util.*

class NodeEvaluator(
    private val atomNodeEvaluator: AtomNodeEvaluator,
    private val userDefinedFunctionNameDeterminer: UserDefinedFunctionNameDeterminer,
    private val functionLengthAsserter: FunctionLengthAsserter
) {

    fun evaluate(
        node: Node,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, Node>,
        areLiteralsAllowed: Boolean
    ): Node {
        return if (node is AtomNode) atomNodeEvaluator.evaluate(
            node,
            variableNameToValueMap
        ) else evaluate(
            node as ExpressionNode,
            userDefinedFunctions,
            variableNameToValueMap,
            areLiteralsAllowed
        )
    }

    private fun evaluate(
        expressionNode: ExpressionNode,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, Node>,
        areLiteralsAllowed: Boolean
    ): Node {
        val address = expressionNode.address
        if (address is AtomNode) {
            val addressValue = address.value
            val isFunctionName = userDefinedFunctionNameDeterminer.determineIfUserDefinedFunctionName(
                userDefinedFunctions,
                addressValue
            )
            if (isFunctionName) {
                val userDefinedFunction = userDefinedFunctions.stream().filter { (_, _, functionName) -> functionName == addressValue }.findFirst().get()
                var params = expressionNode.data
                functionLengthAsserter.assertLengthIsAsExpected(
                    userDefinedFunction.functionName,
                    userDefinedFunction.formalParameters.size + 1,
                    params
                )
                val newVariables: MutableMap<String, Node> = HashMap(variableNameToValueMap)
                for (formal in userDefinedFunction.formalParameters) {
                    val (address1, data) = params as ExpressionNode
                    val evaluatedAddress = evaluate(
                        address1,
                        userDefinedFunctions,
                        variableNameToValueMap,
                        true
                    )
                    newVariables[formal] = evaluatedAddress
                    params = data
                }
                return evaluate(
                    userDefinedFunction.body,
                    userDefinedFunctions,
                    newVariables,
                    true
                )
            }
            if (FunctionsConstants.functionMap!!.containsKey(addressValue)) {
                val function = FunctionsConstants.functionMap[addressValue]
                return function!!.evaluateLispFunction(
                    expressionNode.data,
                    userDefinedFunctions,
                    variableNameToValueMap
                )
            }
            if (!areLiteralsAllowed) throw Exception("Error! Invalid CAR value: $addressValue\n")
        }
        return evaluate(
            address,
            userDefinedFunctions,
            variableNameToValueMap,
            true
        )
    }
}