package com.soapdogg.lispInterpreter.datamodels;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Queue;

@Getter
@AllArgsConstructor(staticName = "newInstance")
public class ProcessedTokensResult {
    private final Queue<String> literalAtoms;
    private final int openCount;
    private final int closeCount;
    private final int numericAtomsSum;
    private final int numericAtomsCount;
}
