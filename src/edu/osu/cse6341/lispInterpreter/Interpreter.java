package edu.osu.cse6341.lispInterpreter;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import edu.osu.cse6341.lispInterpreter.parser.Parser;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.singleton.AsserterSingleton;
import edu.osu.cse6341.lispInterpreter.singleton.GeneratorSingleton;
import edu.osu.cse6341.lispInterpreter.tokenizer.TokenProcessor;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.program.*;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;
import lombok.Getter;

public final class Interpreter{

	private Program program;

	private final Tokenizer tokenizer;
	@Getter private final TokenProcessor tokenProcessor;
	private final Parser parser;

	public Interpreter(){
		tokenizer = new Tokenizer();
		Queue<String> literalAtoms = new LinkedList<>();
		tokenProcessor = TokenProcessor.newInstance(
			literalAtoms,
			0,
			0,
			0,
			0
		);
		parser = Parser.newInstance(
			AsserterSingleton.INSTANCE.getTokenKindAsserter(),
			GeneratorSingleton.INSTANCE.getNodeGenerator()
		);
	}

    void interpret() throws Exception{
		Scanner scanner = new Scanner(System.in);
		interpret(scanner, false, true);
	}

	public void interpret(Scanner in, boolean shouldBeProcessed, boolean shouldBeEvaluated) throws Exception{
	    tokenize(in);
	    if(shouldBeProcessed) {
	    	processTokens();
	    	return;
		}
	    List<LispNode> rootNodes = parser.parse(tokenizer);
	    program = new Program(rootNodes);
        if (shouldBeEvaluated) {
        	program.evaluate();
		}
    }

	private void tokenize(Scanner in){
		tokenizer.tokenize(in);
		in.close();
	}

	private void processTokens() throws Exception{
		tokenProcessor.processTokens(tokenizer.getTokens());
	}

	private Scanner getScannerFromFilePath(String programFilePath){
        Scanner in = null;
	    try {
            in = new Scanner(Paths.get(programFilePath));
        }catch (IOException e){
            System.out.println("File not found");
            System.out.println(programFilePath);
            System.exit(-10);
        }
        return in;
    }

	public String testInterpreter(String programFilePath) throws Exception{
        Scanner in = getScannerFromFilePath(programFilePath);
	    interpret(in, false, true);
		return getValue();
	}

	public String testParser(String programFilePath) throws Exception{
	    Scanner in = getScannerFromFilePath(programFilePath);
	    interpret(in, false, false);
	    return getDotNotation();
    }

	String getValue(){
	    return program.getListNotationToString();
    }

    private String getDotNotation() {
	    return program.getDotNotationToString();
    }
}

 
