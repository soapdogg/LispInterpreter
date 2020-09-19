package edu.osu.cse6341.lispInterpreter.interpreter;

import edu.osu.cse6341.lispInterpreter.datamodels.PartitionedRootNodes;
import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.generator.UserDefinedFunctionGenerator;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import edu.osu.cse6341.lispInterpreter.interpreter.Interpreter;
import edu.osu.cse6341.lispInterpreter.interpreter.RootNodePartitioner;
import edu.osu.cse6341.lispInterpreter.parser.Parser;
import edu.osu.cse6341.lispInterpreter.printer.ListNotationPrinter;
import edu.osu.cse6341.lispInterpreter.evaluator.ProgramEvaluator;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.datamodels.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

class InterpreterTest {

    private Scanner in;

    private Tokenizer tokenizer;
    private Parser parser;
    private ProgramEvaluator program;
    private RootNodePartitioner rootNodePartitioner;
    private UserDefinedFunctionGenerator userDefinedFunctionGenerator;
    private ListNotationPrinter listNotationPrinter;

    private Interpreter interpreter;

    @BeforeEach
    void setup() {
        in = new Scanner(System.in);

        tokenizer = Mockito.mock(Tokenizer.class);
        parser = Mockito.mock(Parser.class);
        program = Mockito.mock(ProgramEvaluator.class);
        rootNodePartitioner = Mockito.mock(RootNodePartitioner.class);
        userDefinedFunctionGenerator = Mockito.mock(UserDefinedFunctionGenerator.class);
        listNotationPrinter = Mockito.mock(ListNotationPrinter.class);

        interpreter = Interpreter.newInstance(
            tokenizer,
            parser,
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
        Mockito.when(parser.parse(tokens)).thenReturn(rootNodes);

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
