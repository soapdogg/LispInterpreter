package edu.osu.cse6341.lispInterpreter;

import edu.osu.cse6341.lispInterpreter.interpreter.Interpreter;
import edu.osu.cse6341.lispInterpreter.singleton.InterpreterSingleton;

import java.util.Scanner;

public class Main{
	
	public static void main(String [] args){
		try {
            Interpreter interpreter = InterpreterSingleton.INSTANCE.getInterpreter();
            Scanner in = new Scanner(System.in);
            String result = interpreter.interpret(in);
            System.out.println(result);
        }catch (Exception e){
		    System.out.println(e.getMessage());
        }
	}
}
