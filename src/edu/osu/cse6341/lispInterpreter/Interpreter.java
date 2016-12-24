package edu.osu.cse6341.lispInterpreter;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

import edu.osu.cse6341.lispInterpreter.IInterpreter;
import edu.osu.cse6341.lispInterpreter.tokenizer.ITokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.program.*;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;

public final class Interpreter implements IInterpreter{

	private static Interpreter singletonInterpreter;
	private IProgram program;
	private ITokenizer tokenizer;

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

 
