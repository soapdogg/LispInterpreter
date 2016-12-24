package edu.osu.cse6341.lispInterpreter;

import edu.osu.cse6341.lispInterpreter.IInterpreter;
import edu.osu.cse6341.lispInterpreter.Interpreter;

public class Main{
	
	public static void main(String [] args){
		IInterpreter interpreter = Interpreter.getInterpreter();
		interpreter.interpret();
		System.out.println(interpreter.toString());
	}
}
