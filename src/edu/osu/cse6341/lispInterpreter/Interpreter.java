package edu.osu.cse6341.lispInterpreter;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

import edu.osu.cse6341.lispInterpreter.IInterpreter;
import edu.osu.cse6341.lispInterpreter.tokenizer.ITokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;

public final class Interpreter implements IInterpreter{

	private static Interpreter singletonInterpreter;
	private ITokenizer tokenizer;
	private Queue<String> literalAtoms;
	private int numericAtomsCount, numericAtomsSum, openCount, closingCount;

	private Interpreter(){
		tokenizer = Tokenizer.getTokenizer();
		literalAtoms = new LinkedList<>();
	}

	public static Interpreter getInterpreter(){
		if(singletonInterpreter == null) singletonInterpreter = new Interpreter();
		return singletonInterpreter;
	}

	public void interpret(){
		tokenize();
		processTokens();
		System.out.println(toString());
	}

	private void tokenize(){
		Scanner inputFileScanner = new Scanner(System.in);
		tokenizer = Tokenizer.getTokenizer();
		tokenizer.tokenize(inputFileScanner);
		inputFileScanner.close();
	}

	private void processTokens(){

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

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("LITERAL ATOMS: ");
		sb.append(literalAtoms.size());
		for(String s : literalAtoms){
			sb.append(',');
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

 
