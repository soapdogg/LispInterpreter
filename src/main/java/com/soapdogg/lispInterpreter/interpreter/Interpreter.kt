package com.soapdogg.lispInterpreter.interpreter

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
    private val listNotationPrinter: ListNotationPrinter
){

    fun interpret(scanner: Scanner): String {
        val tokens = tokenizer.tokenize(scanner)
        val rootNodes = rootParser.parse(tokens)
        val partitionedRootNodes = rootNodePartitioner.partitionRootNodes(
            rootNodes
        )
        val userDefinedFunctions = userDefinedFunctionGenerator.generateUserDefinedFunctions(
            partitionedRootNodes.defunNodes
        )
        val evaluatedNodes = program.evaluate(
            partitionedRootNodes.evaluatableNodes,
            userDefinedFunctions,
            HashMap()
        )
        return listNotationPrinter.printInListNotation(evaluatedNodes)
    }
}