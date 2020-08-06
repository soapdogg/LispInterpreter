package edu.osu.cse6341.lispInterpreter.interpreter;

import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import edu.osu.cse6341.lispInterpreter.parser.Parser;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.printer.ListNotationPrinter;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.program.*;
import edu.osu.cse6341.lispInterpreter.tokens.Token;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public final class Interpreter{

	private final Tokenizer tokenizer;
	private final Parser parser;
	private final Program program;
	private final ListNotationPrinter listNotationPrinter;

	public String interpret(Scanner in) throws Exception{
	    Queue<Token> tokens = tokenizer.tokenize(in);
	    List<LispNode> rootNodes = parser.parse(tokens);
		List<LispNode> evaluatedNodes = program.evaluate(rootNodes);
		return listNotationPrinter.printInListNotation(evaluatedNodes);
    }
}

 
