package edu.osu.cse6341.lispInterpreter.interpreter;

import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import edu.osu.cse6341.lispInterpreter.datamodels.PartitionedRootNodes;
import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.evaluator.ProgramEvaluator;
import edu.osu.cse6341.lispInterpreter.generator.UserDefinedFunctionGenerator;
import edu.osu.cse6341.lispInterpreter.parser.Parser;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import edu.osu.cse6341.lispInterpreter.printer.ListNotationPrinter;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.datamodels.Token;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public final class Interpreter{

	private final Tokenizer tokenizer;
	private final Parser parser;
	private final ProgramEvaluator program;
	private final RootNodePartitioner rootNodePartitioner;
	private final UserDefinedFunctionGenerator userDefinedFunctionGenerator;
	private final ListNotationPrinter listNotationPrinter;

	public String interpret(Scanner in) throws Exception{
	    Queue<Token> tokens = tokenizer.tokenize(in);
	    List<Node> rootNodes = parser.parse(tokens);
		PartitionedRootNodes partitionedRootNodes = rootNodePartitioner.partitionRootNodes(
			rootNodes
		);
		List<UserDefinedFunction> userDefinedFunctions = userDefinedFunctionGenerator.generateUserDefinedFunctions(
			partitionedRootNodes.getDefunNodes()
		);
		List<Node> evaluatedNodes = program.evaluate(
			partitionedRootNodes.getEvaluatableNodes(),
			userDefinedFunctions,
			new HashMap<>()
		);
		return listNotationPrinter.printInListNotation(evaluatedNodes);
    }
}

 
