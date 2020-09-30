package com.soapdogg.lispInterpreter.exceptions;

public class UnexpectedTokenKindException extends Exception {
    public UnexpectedTokenKindException(String message) {
        super(message);
    }
}
