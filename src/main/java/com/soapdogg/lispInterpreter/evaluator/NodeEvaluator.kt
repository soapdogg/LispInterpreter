package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionsConstants
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
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
        variableNameToValueMap: Map<String, NodeV2>
    ): NodeV2 {
        return if (node is AtomNode){
            atomNodeEvaluator.evaluate(
                node,
                variableNameToValueMap
            )
        } else {
            return evaluate(
                node as ExpressionListNode,
                userDefinedFunctions,
                variableNameToValueMap
            )

        }
    }

    private fun evaluate(
        expressionNode: ExpressionListNode,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, NodeV2>
    ): NodeV2 {
        val address = expressionNode.children[0]
        if (address is AtomNode) {
            val v1 = inside(
                address,
                expressionNode,
                userDefinedFunctions,
                variableNameToValueMap
            )
            return nodeConverter.convertNodeToNodeV2(v1)
        }

        return evaluateV2(
            address,
            userDefinedFunctions,
            variableNameToValueMap
        )
    }

    private fun inside(
        address: AtomNode,
        expressionNode: ExpressionListNode,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, NodeV2>
    ): Node {
        val addressValue = address.value
        if (addressValue == ReservedValuesConstants.NIL) {
            return address
        }
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
                    newVariables
                )
                newVariables[formal] = evaluatedAddress
            }

            val v2 = evaluateV2(
                userDefinedFunction.body,
                userDefinedFunctions,
                newVariables
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
        throw Exception("Error! Invalid CAR value: $addressValue\n")
    }
}