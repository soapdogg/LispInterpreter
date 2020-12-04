package com.soapdogg.lispInterpreter.asserter

import com.soapdogg.lispInterpreter.converter.NodeConverter
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.determiner.FunctionLengthDeterminer
import com.soapdogg.lispInterpreter.exceptions.WrongFunctionLengthException

class FunctionLengthAsserter (
    private val functionLengthDeterminer: FunctionLengthDeterminer,
    private val nodeConverter: NodeConverter
){
    fun assertLengthIsAsExpected(
        functionName: String,
        expected: Int,
        node: Node
    ) {
        val converted = nodeConverter.convertNodeToNodeV2(node)
        val actual = functionLengthDeterminer.determineFunctionLength(converted)
        if (actual != expected - 1) {
            val sb = """Error! Expected length of $functionName list is $expected!    Actual: ${actual + 1}${'\n'}"""
            throw WrongFunctionLengthException(sb)
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