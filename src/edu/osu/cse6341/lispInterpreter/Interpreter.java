package edu.osu.cse6341.lispInterpreter;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import edu.osu.cse6341.lispInterpreter.datamodels.ProcessedTokensResult;
import edu.osu.cse6341.lispInterpreter.parser.Parser;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.singleton.AsserterSingleton;
import edu.osu.cse6341.lispInterpreter.singleton.GeneratorSingleton;
import edu.osu.cse6341.lispInterpreter.tokenizer.TokenProcessor;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.program.*;
import edu.osu.cse6341.lispInterpreter.tokens.Token;
import lombok.Getter;

public final class Interpreter{

	private Program program;

	private final Tokenizer tokenizer;
	@Getter private final TokenProcessor tokenProcessor;
	private final Parser parser;

	public Interpreter(){
		tokenizer = new Tokenizer();
		tokenProcessor = TokenProcessor.newInstance();
		parser = Parser.newInstance(
			AsserterSingleton.INSTANCE.getTokenKindAsserter(),
			GeneratorSingleton.INSTANCE.getNodeGenerator()
		);
	}

    void interpret() throws Exception{
		Scanner scanner = new Scanner(System.in);
		interpret(scanner,  true);
	}

	public void interpret(Scanner in,  boolean shouldBeEvaluated) throws Exception{
	    Queue<Token> tokens = tokenizer.tokenize(in);
	    List<LispNode> rootNodes = parser.parse(tokens);
	    program = new Program(rootNodes);
        if (shouldBeEvaluated) {
        	program.evaluate();
		}
    }


	public ProcessedTokensResult processTokens(
		Scanner in
	) throws Exception{
		Queue<Token> tokens = tokenizer.tokenize(in);
		return tokenProcessor.processTokens(tokens);
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
	    interpret(in,  true);
		return getValue();
	}

	public String testParser(String programFilePath) throws Exception{
	    Scanner in = getScannerFromFilePath(programFilePath);
	    interpret(in,  false);
	    return getDotNotation();
    }

	String getValue(){
	    return program.getListNotationToString();
    }

    private String getDotNotation() {
	    return program.getDotNotationToString();
    }
}

 
