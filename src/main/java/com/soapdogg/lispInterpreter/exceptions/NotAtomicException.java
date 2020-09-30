package com.soapdogg.lispInterpreter.exceptions;

public class NotAtomicException extends Exception {
    public NotAtomicException(String message) {
        super(message);
    }
}
