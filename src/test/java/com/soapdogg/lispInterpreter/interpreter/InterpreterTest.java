package com.soapdogg.lispInterpreter.interpreter;

import com.soapdogg.lispInterpreter.datamodels.PartitionedRootNodes;
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction;
import com.soapdogg.lispInterpreter.generator.UserDefinedFunctionGenerator;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.parser.RootParser;
import com.soapdogg.lispInterpreter.printer.ListNotationPrinter;
import com.soapdogg.lispInterpreter.evaluator.ProgramEvaluator;
import com.soapdogg.lispInterpreter.tokenizer.Tokenizer;
import com.soapdogg.lispInterpreter.datamodels.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

class InterpreterTest {

    private Scanner in;

    private Tokenizer tokenizer;
    private RootParser rootParser;
    private ProgramEvaluator program;
    private RootNodePartitioner rootNodePartitioner;
    private UserDefinedFunctionGenerator userDefinedFunctionGenerator;
    private ListNotationPrinter listNotationPrinter;

    private Interpreter interpreter;

    @BeforeEach
    void setup() {
        in = new Scanner(System.in);

        tokenizer = Mockito.mock(Tokenizer.class);
        rootParser = Mockito.mock(RootParser.class);
        program = Mockito.mock(ProgramEvaluator.class);
        rootNodePartitioner = Mockito.mock(RootNodePartitioner.class);
        userDefinedFunctionGenerator = Mockito.mock(UserDefinedFunctionGenerator.class);
        listNotationPrinter = Mockito.mock(ListNotationPrinter.class);

        interpreter = Interpreter.newInstance(
            tokenizer,
            rootParser,
            program,
            rootNodePartitioner,
            userDefinedFunctionGenerator,
            listNotationPrinter
        );
    }

    @Test
    void interpretTest() throws Exception {
        Queue<Token> tokens = new LinkedList<>();
        Mockito.when(tokenizer.tokenize(in)).thenReturn(tokens);

        List<Node> rootNodes = Collections.emptyList();
        Mockito.when(rootParser.parse(tokens)).thenReturn(rootNodes);

        PartitionedRootNodes partitionedRootNodes = Mockito.mock(PartitionedRootNodes.class);
        Mockito.when(rootNodePartitioner.partitionRootNodes(rootNodes)).thenReturn(partitionedRootNodes);

        List<Node> defunNodes = Collections.emptyList();
        Mockito.when(partitionedRootNodes.getDefunNodes()).thenReturn(defunNodes);

        List<UserDefinedFunction> userDefinedFunctions = Collections.emptyList();
        Mockito.when(userDefinedFunctionGenerator.generateUserDefinedFunctions(defunNodes)).thenReturn(userDefinedFunctions);

        List<Node> evaluatedNodes = Collections.emptyList();
        Mockito.when(partitionedRootNodes.getEvaluatableNodes()).thenReturn(evaluatedNodes);

        Mockito.when(
            program.evaluate(
                evaluatedNodes,
                userDefinedFunctions,
                new HashMap<>()
            )
        ).thenReturn(evaluatedNodes);

        String value = "value";
        Mockito.when(listNotationPrinter.printInListNotation(evaluatedNodes)).thenReturn(value);

        String actual = interpreter.interpret(in);

        Assertions.assertEquals(value, actual);
    }
}
