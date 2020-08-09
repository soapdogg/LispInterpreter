package edu.osu.cse6341.lispInterpreter.datamodels;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Queue;

@Getter
@AllArgsConstructor(staticName = "newInstance")
public class TokenizedLineResult {
    private final boolean continueProcessing;
    private Queue<Token> tokens;
}
