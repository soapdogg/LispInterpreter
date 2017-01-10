package edu.osu.cse6341.lispInterpreter;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.program.*;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;

public final class Interpreter{

	private Program program;
	private Queue<String> literalAtoms;
	private int numericAtomsCount, numericAtomsSum, openCount, closingCount;
	private Tokenizer tokenizer;
	private String errorMessage;
	private boolean hasError;

	public Interpreter(){
		tokenizer = new Tokenizer();
		program = new Program();
		literalAtoms = new LinkedList<>();
		hasError = false;
	}

    void interpret(){
		Scanner scanner = new Scanner(System.in);
		interpret(scanner, false, true);
	}

	private void interpret(Scanner in, boolean shouldBeProcessed, boolean shouldBeEvaluated){
	    tokenize(in);
	    if(shouldBeProcessed) processTokens();
	    else {
	        program.parse(tokenizer);
	        hasError = program.hasError();
	        if(hasError) errorMessage = program.getErrorMessage();
        }
        if (shouldBeEvaluated && !hasError) program.evaluate();
    }

	private void tokenize(Scanner in){
		tokenizer.tokenize(in);
		in.close();
	}

	private void processTokens(){
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

	String testInterpreter(String programFilePath) {
        Scanner in = getScannerFromFilePath(programFilePath);
	    interpret(in, false, true);
		return getValue();
	}

	String testParser(String programFilePath){
	    Scanner in = getScannerFromFilePath(programFilePath);
	    interpret(in, false, false);
	    return getDotNotation();
    }

	String testTokenizer(String programFilePath){
        Scanner in = getScannerFromFilePath(programFilePath);
        interpret(in, true, false);
        return getTokenizedResults();
    }

	String getValue(){
	    return program.getValue();
    }

    String getDotNotation() {
	    if(hasError) return program.getErrorMessage();
	    return program.getDotNotation();
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

	public void setErrorMessage(String errorMessage){
	    this.errorMessage = errorMessage;
	    hasError = true;
    }

    private String getTokenizedResults(){
		if(hasError) return errorMessage;
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

 
