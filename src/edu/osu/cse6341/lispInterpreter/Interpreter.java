package edu.osu.cse6341.lispInterpreter;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.program.*;

public final class Interpreter{

	private static Interpreter singletonInterpreter;
	private Program program;
	private Tokenizer tokenizer;

	private Interpreter(){
		tokenizer = Tokenizer.getTokenizer();
		program = Program.getProgram();
	}

	static Interpreter getInterpreter(){
		if(singletonInterpreter == null) singletonInterpreter = new Interpreter();
		return singletonInterpreter;
	}

	void interpret(){
		Scanner scanner = new Scanner(System.in);
	    tokenize(scanner);
		program.parse();
		program.evaluate();
	}

	private void interpret(Scanner in){
	    tokenize(in);
	    program.parse();
	    program.evaluate();
    }

	private void tokenize(Scanner in){
		tokenizer = Tokenizer.getTokenizer();
		tokenizer.tokenize(in);
		in.close();
	}

	String testInterpreter(String programFilePath) {
        Scanner in = null;
	    try {
            in = new Scanner(Paths.get(programFilePath));
        }catch (IOException e){
            System.out.println("File not found");
            System.out.println(programFilePath);
            System.exit(-10);
        }
	    interpret(in);
		Program program = Program.getProgram();
		return program.toString();
	}

	@Override
	public String toString(){
		return program.toString(); 
	}
}

 
