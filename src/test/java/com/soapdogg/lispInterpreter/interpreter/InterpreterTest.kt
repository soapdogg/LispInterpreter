package com.soapdogg.lispInterpreter.interpreter

import com.soapdogg.lispInterpreter.converter.NodeConverter
import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.evaluator.ProgramEvaluator
import com.soapdogg.lispInterpreter.generator.UserDefinedFunctionGenerator
import com.soapdogg.lispInterpreter.parser.RootParser
import com.soapdogg.lispInterpreter.printer.ListNotationPrinter
import com.soapdogg.lispInterpreter.tokenizer.Tokenizer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.Scanner

class InterpreterTest {
    private val scanner: Scanner = Mockito.mock(Scanner::class.java)
    private val tokenizer: Tokenizer = Mockito.mock(Tokenizer::class.java)
    private val rootParser: RootParser = Mockito.mock(RootParser::class.java)
    private val program: ProgramEvaluator = Mockito.mock(ProgramEvaluator::class.java)
    private val rootNodePartitioner: RootNodePartitioner = Mockito.mock(RootNodePartitioner::class.java)
    private val userDefinedFunctionGenerator: UserDefinedFunctionGenerator = Mockito.mock(UserDefinedFunctionGenerator::class.java)
    private val nodeConverter = Mockito.mock(NodeConverter::class.java)
    private val listNotationPrinter: ListNotationPrinter = Mockito.mock(ListNotationPrinter::class.java)
    private val interpreter: Interpreter = Interpreter(
        tokenizer,
        rootParser,
        program,
        rootNodePartitioner,
        userDefinedFunctionGenerator,
        nodeConverter,
        listNotationPrinter
    )

    @Test
    fun interpretTest() {
        val tokens: List<Token> = listOf()
        Mockito.`when`(tokenizer.tokenize(scanner)).thenReturn(tokens)

        val rootNodes= listOf<NodeV2>()
        Mockito.`when`(rootParser.parse(tokens)).thenReturn(rootNodes)

        val partitionedRootNodes = Mockito.mock(PartitionedRootNodes::class.java)
        Mockito.`when`(rootNodePartitioner.partitionRootNodes(rootNodes)).thenReturn(partitionedRootNodes)

        val defunNodes: List<Node> = listOf()
        Mockito.`when`(partitionedRootNodes.defunNodes).thenReturn(defunNodes)

        val userDefinedFunctions: List<UserDefinedFunction> = listOf()
        Mockito.`when`(userDefinedFunctionGenerator.generateUserDefinedFunctions(defunNodes)).thenReturn(userDefinedFunctions)

        val node = Mockito.mock(Node::class.java)
        val evaluatedNodes: List<Node> = listOf(node)
        Mockito.`when`(partitionedRootNodes.evaluatableNodes).thenReturn(evaluatedNodes)
        Mockito.`when`(
            program.evaluate(
                evaluatedNodes,
                userDefinedFunctions,
                HashMap()
            )
        ).thenReturn(evaluatedNodes)

        val nodeV2 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(nodeConverter.convertNodeToNodeV2(node)).thenReturn(nodeV2)

        val convertedNodes = listOf(nodeV2)
        val value = "value"
        Mockito.`when`(listNotationPrinter.printInListNotation(convertedNodes)).thenReturn(value)

        val actual = interpreter.interpret(scanner)
        Assertions.assertEquals(value, actual)
    }
}