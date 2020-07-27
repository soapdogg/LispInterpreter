package edu.osu.cse6341.lispInterpreter.exceptions;

public class NotAtomicException extends Exception {
    public NotAtomicException(String message) {
        super(message);
    }
}
