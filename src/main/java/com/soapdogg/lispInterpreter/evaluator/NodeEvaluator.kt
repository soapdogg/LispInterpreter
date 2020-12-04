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
        return if (node is AtomNode){
            atomNodeEvaluator.evaluate(
                node,
                variableNameToValueMap
            )
        } else {
            val v1 = evaluate(
                node as ExpressionListNode,
                userDefinedFunctions,
                variableNameToValueMap,
                areLiteralsAllowed
            )
            nodeConverter.convertNodeToNodeV2(v1)
        }
    }

    private fun evaluate(
        expressionNode: ExpressionListNode,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, NodeV2>,
        areLiteralsAllowed: Boolean
    ): Node {
        val address = expressionNode.children[0]
        if (address is AtomNode) {
            val addressValue = address.value
            val isFunctionName = userDefinedFunctionNameDeterminer.determineIfUserDefinedFunctionName(
                userDefinedFunctions,
                addressValue
            )



            if (isFunctionName) {
                val userDefinedFunction = userDefinedFunctions.stream().filter { (_, _, functionName) -> functionName == addressValue }.findFirst().get()
                val params = ExpressionListNode(expressionNode.children.subList(1, expressionNode.children.size))
                functionLengthAsserter.assertLengthIsAsExpected(
                    userDefinedFunction.functionName,
                    userDefinedFunction.formalParameters.size,
                    params
                )
                val newVariables: MutableMap<String, NodeV2> = HashMap(variableNameToValueMap)
                for ((index, formal) in userDefinedFunction.formalParameters.withIndex()) {
                    val a = params.children[index]
                    val evaluatedAddress = evaluateV2(
                        a,
                        userDefinedFunctions,
                        newVariables,
                        true
                    )
                    newVariables[formal] = evaluatedAddress
                }

                val v2 = evaluateV2(
                    userDefinedFunction.body,
                    userDefinedFunctions,
                    newVariables,
                    true
                )
                return nodeConverter.convertNodeV2ToNode(v2)
            }


            if (FunctionsConstants.functionV2Map!!.containsKey(addressValue)) {
                val function = FunctionsConstants.functionV2Map[addressValue]
                val evaluatedV2 = function!!.evaluateLispFunction(
                    expressionNode,
                    userDefinedFunctions,
                    variableNameToValueMap
                )
                return nodeConverter.convertNodeV2ToNode(evaluatedV2)
            }
            if (!areLiteralsAllowed) throw Exception("Error! Invalid CAR value: $addressValue\n")
        }

        val v2 = evaluateV2(
            address,
            userDefinedFunctions,
            variableNameToValueMap,
            true
        )
        return nodeConverter.convertNodeV2ToNode(v2)
    }
}