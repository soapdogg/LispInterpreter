package com.soapdogg.lispInterpreter.interpreter

import com.soapdogg.lispInterpreter.converter.NodeConverter
import com.soapdogg.lispInterpreter.evaluator.ProgramEvaluator
import com.soapdogg.lispInterpreter.functions.DefunFunction
import com.soapdogg.lispInterpreter.parser.RootParser
import com.soapdogg.lispInterpreter.printer.ListNotationPrinter
import com.soapdogg.lispInterpreter.tokenizer.Tokenizer
import java.util.Scanner

class Interpreter (
    private val tokenizer: Tokenizer,
    private val rootParser: RootParser,
    private val program: ProgramEvaluator,
    private val rootNodePartitioner: RootNodePartitioner,
    private val defunFunction: DefunFunction,
    private val nodeConverter: NodeConverter,
    private val listNotationPrinter: ListNotationPrinter
){

    fun interpret(scanner: Scanner): String {
        val tokens = tokenizer.tokenize(scanner)
        val rootNodes = rootParser.parse(tokens)

        val partitionedRootNodes = rootNodePartitioner.partitionRootNodes(
            rootNodes
        )
        val userDefinedFunctions = partitionedRootNodes.defunNodes.map{defunFunction.evaluateLispFunction(it) }
        val evaluatedNodes = program.evaluate(
            partitionedRootNodes.evaluatableNodes,
            userDefinedFunctions,
            HashMap()
        )
        val convertedNodes = evaluatedNodes.map { nodeConverter.convertNodeToNodeV2(it) }
        return listNotationPrinter.printInListNotation(convertedNodes)
    }
}