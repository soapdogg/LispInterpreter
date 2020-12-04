package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionsConstants
import com.soapdogg.lispInterpreter.converter.NodeConverter
import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.determiner.UserDefinedFunctionNameDeterminer
import java.util.*

class NodeEvaluator(
    private val atomNodeEvaluator: AtomNodeEvaluator,
    private val userDefinedFunctionNameDeterminer: UserDefinedFunctionNameDeterminer,
    private val functionLengthAsserter: FunctionLengthAsserter,
    private val nodeConverter: NodeConverter
) {

    fun evaluateV2(
        node: NodeV2,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, NodeV2>,
        areLiteralsAllowed: Boolean
    ): NodeV2 {
        val convertedVariables = variableNameToValueMap.map {
            Pair(it.key, nodeConverter.convertNodeV2ToNode(it.value))
        }.toMap()
        val v1 = evaluate(
            node,
            userDefinedFunctions,
            convertedVariables,
            areLiteralsAllowed
        )
        return nodeConverter.convertNodeToNodeV2(v1)
    }

    fun evaluate(
        node: NodeV2,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, Node>,
        areLiteralsAllowed: Boolean
    ): Node {
        return if (node is AtomNode){
            val convertedVariables = variableNameToValueMap.map {
                Pair(it.key, nodeConverter.convertNodeToNodeV2(it.value))
            }.toMap()
            val v2 = atomNodeEvaluator.evaluate(
                node,
                convertedVariables
            )
            nodeConverter.convertNodeV2ToNode(v2)
        } else evaluate(
            nodeConverter.convertNodeV2ToNode(node) as ExpressionNode,
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
                        nodeConverter.convertNodeToNodeV2(address1),
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
            if (FunctionsConstants.functionV2Map!!.containsKey(addressValue)) {
                val convertedParams = nodeConverter.convertNodeToNodeV2(expressionNode)
                val convertedVariables = variableNameToValueMap.map {
                    Pair(it.key, nodeConverter.convertNodeToNodeV2(it.value))
                }.toMap()
                val function = FunctionsConstants.functionV2Map[addressValue]
                val evaluatedV2 = function!!.evaluateLispFunction(
                    convertedParams as ExpressionListNode,
                    userDefinedFunctions,
                    convertedVariables
                )
                return nodeConverter.convertNodeV2ToNode(evaluatedV2)
            }
            if (!areLiteralsAllowed) throw Exception("Error! Invalid CAR value: $addressValue\n")
        }
        return evaluate(
            nodeConverter.convertNodeToNodeV2(address),
            userDefinedFunctions,
            variableNameToValueMap,
            true
        )
    }
}