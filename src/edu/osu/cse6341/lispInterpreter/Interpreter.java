package edu.osu.cse6341.lispInterpreter;

import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import edu.osu.cse6341.lispInterpreter.parser.Parser;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.printer.DotNotationPrinter;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.program.*;
import edu.osu.cse6341.lispInterpreter.tokens.Token;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public final class Interpreter{

	private final Tokenizer tokenizer;
	private final Parser parser;
	private final DotNotationPrinter dotNotationPrinter;

	public String interpret(Scanner in,  boolean shouldBeEvaluated) throws Exception{
	    Queue<Token> tokens = tokenizer.tokenize(in);
	    List<LispNode> rootNodes = parser.parse(tokens);
	    Program program = new Program(rootNodes);
        if (shouldBeEvaluated) {
        	program.evaluate();
        	return program.getListNotationToString();
		}
        return  dotNotationPrinter.printInDotNotation(rootNodes);
    }
}

 
