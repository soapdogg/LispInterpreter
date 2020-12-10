package com.soapdogg.lispInterpreter.interpreter

import com.soapdogg.lispInterpreter.converter.NodeToStackConverter
import com.soapdogg.lispInterpreter.evaluator.ProgramEvaluator
import com.soapdogg.lispInterpreter.generator.UserDefinedFunctionGenerator
import com.soapdogg.lispInterpreter.parser.RootParser
import com.soapdogg.lispInterpreter.printer.ListNotationPrinter
import com.soapdogg.lispInterpreter.tokenizer.Tokenizer
import java.util.Scanner

class Interpreter (
    private val tokenizer: Tokenizer,
    private val rootParser: RootParser,
    private val nodeToStackConverter: NodeToStackConverter,
    private val program: ProgramEvaluator,
    private val rootNodePartitioner: RootNodePartitioner,
    private val userDefinedFunctionGenerator: UserDefinedFunctionGenerator,
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

        if (useStackEval) {
            val stacks = partitionedRootNodes.evaluatableNodes.map { nodeToStackConverter.convertToStack(it) }
            val evaluatedStacks = program.evaluateStacks(
                stacks
            )
            println(evaluatedStacks)
        }
        val evaluatedNodes = program.evaluate(
            partitionedRootNodes.evaluatableNodes,
            userDefinedFunctions,
            HashMap()
        )

        return listNotationPrinter.printInListNotation(evaluatedNodes)
    }
}