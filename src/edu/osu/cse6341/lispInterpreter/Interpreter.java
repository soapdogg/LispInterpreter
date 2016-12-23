package edu.osu.cse6341.lispInterpreter;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

import edu.osu.cse6341.lispInterpreter.IInterpreter;
import edu.osu.cse6341.lispInterpreter.tokenizer.ITokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;

public final class Interpreter implements IInterpreter{

	private static Interpreter singletonInterpreter;
	private ITokenizer tokenizer;

	private Interpreter(){
		tokenizer = Tokenizer.getTokenizer();
	}

	public static Interpreter getInterpreter(){
		if(singletonInterpreter == null) singletonInterpreter = new Interpreter();
		return singletonInterpreter;
	}

	public void interpret(){
		tokenize();
		System.out.println(toString());
	}

	private void tokenize(){
		Scanner inputFileScanner = new Scanner(System.in);
		tokenizer = Tokenizer.getTokenizer();
		tokenizer.tokenize(inputFileScanner);
		inputFileScanner.close();
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}
}

 
