package com.soapdogg.lispInterpreter.interpreter

import com.soapdogg.lispInterpreter.asserter.ExpressionListLengthAsserter
import com.soapdogg.lispInterpreter.evaluator.ProgramEvaluator
import com.soapdogg.lispInterpreter.generator.UserDefinedFunctionGenerator
import com.soapdogg.lispInterpreter.parser.RootParser
import com.soapdogg.lispInterpreter.printer.ListNotationPrinter
import com.soapdogg.lispInterpreter.tokenizer.Tokenizer
import java.util.Scanner

class Interpreter(
    private val tokenizer: Tokenizer,
    private val rootParser: RootParser,
    private val rootNodePartitioner: RootNodePartitioner,
    private val userDefinedFunctionGenerator: UserDefinedFunctionGenerator,
    private val expressionListLengthAsserter: ExpressionListLengthAsserter,
    private val programEvaluator: ProgramEvaluator,
    private val listNotationPrinter: ListNotationPrinter
){

    fun interpret(
        scanner: Scanner
    ): String {
        val tokens = tokenizer.tokenize(scanner)
        val rootNodes = rootParser.parse(tokens)

        val partitionedRootNodes = rootNodePartitioner.partitionRootNodes(
            rootNodes
        )
        val userDefinedFunctions = partitionedRootNodes.defunNodes.map{
            userDefinedFunctionGenerator.evaluateLispFunction(it) }.toMap()

        expressionListLengthAsserter.assertLengthIsAsExpected(
            partitionedRootNodes.evaluatableNodes,
            userDefinedFunctions
        )

        val evaluatedNodes = programEvaluator.evaluatePostOrder(
            partitionedRootNodes.evaluatableNodes,
            userDefinedFunctions
        )

        return listNotationPrinter.printInListNotation(evaluatedNodes)
    }
}