package edu.osu.cse6341.lispInterpreter;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import edu.osu.cse6341.lispInterpreter.parser.Parser;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.singleton.AsserterSingleton;
import edu.osu.cse6341.lispInterpreter.singleton.GeneratorSingleton;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.program.*;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;

public final class Interpreter{

	private Program program;
	private final Queue<String> literalAtoms;
	private int numericAtomsCount, numericAtomsSum, openCount, closingCount;
	private final Tokenizer tokenizer;
	private final Parser parser;

	public Interpreter(){
		tokenizer = new Tokenizer();
		literalAtoms = new LinkedList<>();
		parser = Parser.newInstance(
			AsserterSingleton.INSTANCE.getTokenKindAsserter(),
			GeneratorSingleton.INSTANCE.getNodeGenerator()
		);
	}

    void interpret() throws Exception{
		Scanner scanner = new Scanner(System.in);
		interpret(scanner, false, true);
	}

	private void interpret(Scanner in, boolean shouldBeProcessed, boolean shouldBeEvaluated) throws Exception{
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
		while(tokenizer.hasNext()){
			IToken token = tokenizer.getNextToken();
			token.process(this);
		}
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

	public String testTokenizer(String programFilePath) throws Exception{
        Scanner in = getScannerFromFilePath(programFilePath);
        interpret(in, true, false);
        return getTokenizedResults();
    }

	String getValue(){
	    return program.getListNotationToString(false);
    }

    private String getDotNotation() {
	    return program.getDotNotationToString();
    }

    public void incrementOpenCount(){
		++openCount;
	}

	public void incrementClosingCount(){
    	++closingCount;
	}

	public void incrementNumericAtomCount(){
		++numericAtomsCount;
	}

	public void addToNumericAtomSum(int value){
		numericAtomsSum += value;
	}

	public void addToLiteralAtoms(String atomValue){
		literalAtoms.add(atomValue);
	}


    private String getTokenizedResults(){
	    StringBuilder sb = new StringBuilder();
		sb.append("LITERAL ATOMS: ");
		sb.append(literalAtoms.size());
		for(String s : literalAtoms){
			sb.append(',');
			sb.append(' ');
			sb.append(s);
		}
		sb.append('\n');
		sb.append("NUMERIC ATOMS: ");
		sb.append(numericAtomsCount);
		sb.append(',');
		sb.append(numericAtomsSum);
		sb.append('\n');
		sb.append("OPEN PARENTHESES: ");
		sb.append(openCount);
		sb.append('\n');
		sb.append("CLOSING PARENTHESES: ");
		sb.append(closingCount);
		sb.append('\n');
		return sb.toString();
	}
}

 
