package edu.osu.cse6341.lispInterpreter.exceptions;

public class UnexpectedTokenKindException extends Exception {
    public UnexpectedTokenKindException(String message) {
        super(message);
    }
}
