package com.soapdogg.lispInterpreter.asserter

import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.determiner.FunctionLengthDeterminer
import com.soapdogg.lispInterpreter.exceptions.WrongFunctionLengthException

class FunctionLengthAsserter (
    private val functionLengthDeterminer: FunctionLengthDeterminer
){

    fun assertLengthIsAsExpected(
        functionName: String,
        expected: Int,
        node: Node
    ) {
        val actual = functionLengthDeterminer.determineFunctionLength(node)
        if (actual != expected - 1) {
            val sb = """Error! Expected length of $functionName list is $expected!    Actual: ${actual + 1}${'\n'}"""
            throw WrongFunctionLengthException(sb)
        }
    }
}