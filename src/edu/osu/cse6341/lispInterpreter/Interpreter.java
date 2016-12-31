package edu.osu.cse6341.lispInterpreter;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.program.*;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;

public final class Interpreter{

	private static Interpreter singletonInterpreter;
	private Program program;
	private Tokenizer tokenizer;

	private Interpreter(){
		tokenizer = Tokenizer.getTokenizer();
		program = Program.getProgram();
	}

	public static Interpreter getInterpreter(){
		if(singletonInterpreter == null) singletonInterpreter = new Interpreter();
		return singletonInterpreter;
	}

	public void interpret(){
		tokenize();
		program.parse();
	}

	private void tokenize(){
		Scanner inputFileScanner = new Scanner(System.in);
		tokenizer = Tokenizer.getTokenizer();
		tokenizer.tokenize(inputFileScanner);
		inputFileScanner.close();
	}
	
	@Override
	public String toString(){
		return program.toString(); 
	}
}

 
