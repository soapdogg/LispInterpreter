package edu.osu.cse6341.lispInterpreter.exceptions;

public class WrongFunctionLengthException extends Exception {

    public WrongFunctionLengthException(String message) {
        super(message);
    }
}
