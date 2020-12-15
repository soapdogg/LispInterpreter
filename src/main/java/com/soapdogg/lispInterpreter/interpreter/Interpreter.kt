package com.soapdogg.lispInterpreter.interpreter

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.evaluator.ProgramEvaluator
import com.soapdogg.lispInterpreter.generator.UserDefinedFunctionGenerator
import com.soapdogg.lispInterpreter.parser.RootParser
import com.soapdogg.lispInterpreter.printer.ListNotationPrinter
import com.soapdogg.lispInterpreter.tokenizer.Tokenizer
import java.util.Scanner

class Interpreter (
    private val tokenizer: Tokenizer,
    private val rootParser: RootParser,
    private val program: ProgramEvaluator,
    private val rootNodePartitioner: RootNodePartitioner,
    private val userDefinedFunctionGenerator: UserDefinedFunctionGenerator,
    private val functionLengthAsserter: FunctionLengthAsserter,
    private val listNotationPrinter: ListNotationPrinter
){

    fun interpret(
        scanner: Scanner,
        useStackEval: Boolean
    ): String {
        val tokens = tokenizer.tokenize(scanner)
        val rootNodes = rootParser.parse(tokens)

        val partitionedRootNodes = rootNodePartitioner.partitionRootNodes(
            rootNodes
        )
        val userDefinedFunctions = partitionedRootNodes.defunNodes.map{
            userDefinedFunctionGenerator.evaluateLispFunction(it) }.toMap()

        functionLengthAsserter.assertLengthIsAsExpected(
            partitionedRootNodes.evaluatableNodes,
            userDefinedFunctions
        )

        if (useStackEval) {
            partitionedRootNodes.evaluatableNodes.forEach {
                if (it is ExpressionListNode) {
                    program.evaluatePostOrder(
                        it
                    )
                }
            }
        }
        val evaluatedNodes = program.evaluate(
            partitionedRootNodes.evaluatableNodes,
            userDefinedFunctions,
            HashMap()
        )

        return listNotationPrinter.printInListNotation(evaluatedNodes)
    }
}