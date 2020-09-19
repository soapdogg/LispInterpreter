package edu.osu.cse6341.lispInterpreter.datamodels;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "newInstance")
public class ProcessedStateResult {
    private final Token token;
    private final int startingPos;
}
