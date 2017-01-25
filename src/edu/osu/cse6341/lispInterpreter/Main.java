package edu.osu.cse6341.lispInterpreter;

public class Main{
	
	public static void main(String [] args){
		try {
            Interpreter interpreter = new Interpreter();
            interpreter.interpret();
            System.out.println(interpreter.getDotNotation());
        }catch (Exception e){
		    System.out.println(e.getMessage());
        }
	}
}
