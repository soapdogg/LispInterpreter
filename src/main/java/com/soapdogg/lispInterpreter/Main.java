package com.soapdogg.lispInterpreter;

import com.soapdogg.lispInterpreter.interpreter.Interpreter;
import com.soapdogg.lispInterpreter.singleton.InterpreterSingleton;

import java.util.Scanner;

public class Main {
	
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
