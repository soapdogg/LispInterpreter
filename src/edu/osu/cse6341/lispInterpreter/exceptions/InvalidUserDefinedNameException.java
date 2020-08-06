package edu.osu.cse6341.lispInterpreter.exceptions;

public class InvalidUserDefinedNameException extends Exception {
    public InvalidUserDefinedNameException(String message) {
        super(message);
    }
}
