package com.soapdogg.lispInterpreter.asserter

import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.constants.FunctionsConstants
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.determiner.FunctionLengthDeterminer
import com.soapdogg.lispInterpreter.exceptions.NotAListException
import com.soapdogg.lispInterpreter.exceptions.WrongFunctionLengthException

class FunctionLengthAsserter (
    private val functionLengthDeterminer: FunctionLengthDeterminer
){
    fun assertLengthIsAsExpected(
        nodes: List<NodeV2>,
        userDefinedFunctions: Map<String, UserDefinedFunction>
    ) {
        nodes.forEach { node ->
            if (node is ExpressionListNode) {
                val address = node.children[0]
                    if(address is AtomNode) {
                        val addressValue = address.value
                        FunctionsConstants.functionLengthMap[addressValue]?.let {
                            assertLengthIsAsExpected(
                                addressValue,
                                it,
                                node
                            )
                        }
                        userDefinedFunctions[addressValue]?.let {
                            assertLengthIsAsExpected(
                                addressValue,
                                it.formalParameters.size + 1,
                                node
                            )
                        }
                        if (addressValue == FunctionNameConstants.COND) {
                            for (i in 1 until node.children.size) {
                                val condParameter = node.children[i]
                                if (condParameter is AtomNode) {
                                    if (condParameter.value != ReservedValuesConstants.NIL) {
                                        throw NotAListException("Error! COND parameter: ${condParameter.value} is not a list!${'\n'}")
                                    }
                                } else {
                                    assertLengthIsAsExpected(
                                        FunctionNameConstants.COND,
                                        FunctionLengthConstants.TWO,
                                        condParameter
                                    )
                                }
                            }
                        }
                    }
                assertLengthIsAsExpected(
                    node.children,
                    userDefinedFunctions
                )
            }
        }
    }

    fun assertLengthIsAsExpected(
        functionName: String,
        expected: Int,
        node: NodeV2
    ) {
        val actual = functionLengthDeterminer.determineFunctionLength(node)
        if (actual != expected) {
            val sb = """Error! Expected length of $functionName list is $expected!    Actual: ${actual}${'\n'}"""
            throw WrongFunctionLengthException(sb)
        }
    }
}