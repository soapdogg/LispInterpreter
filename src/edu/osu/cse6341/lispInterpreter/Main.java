package edu.osu.cse6341.lispInterpreter;

public class Main{
	
	public static void main(String [] args){
		Interpreter interpreter = Interpreter.getInterpreter();
		interpreter.interpret();
		System.out.println(interpreter.getValue());
	}
}
