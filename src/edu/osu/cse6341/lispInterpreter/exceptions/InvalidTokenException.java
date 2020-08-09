package edu.osu.cse6341.lispInterpreter.exceptions;

import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;

public class InvalidTokenException extends Exception{

    public InvalidTokenException(String message) {
        super(message);
    }
}
