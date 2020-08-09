package edu.osu.cse6341.lispInterpreter.tokenizer;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

import edu.osu.cse6341.lispInterpreter.datamodels.ProcessedStateResult;
import edu.osu.cse6341.lispInterpreter.datamodels.TokenizedLineResult;
import edu.osu.cse6341.lispInterpreter.tokenizer.states.*;
import edu.osu.cse6341.lispInterpreter.datamodels.Token;
import edu.osu.cse6341.lispInterpreter.datamodels.TokenKind;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class Tokenizer {

    private final ScannerToLineTransformer scannerToLineTransformer;
    private final LineTokenizer lineTokenizer;

	public Queue<Token> tokenize(Scanner in){
		Queue<Token> tokens = new LinkedList<>();
        boolean continueParsing = true;
        List<String> lines = scannerToLineTransformer.transformScannerInputToLines(
            in
        );
        for(int i = 0; i < lines.size() && continueParsing; ++i) {
            String line = lines.get(i);
            TokenizedLineResult tokenizedLineResult = lineTokenizer.tokenizeLine(
                line
            );
            continueParsing = tokenizedLineResult.isContinueProcessing();
            tokens.addAll(tokenizedLineResult.getTokens());
        }
        return tokens;
	}
}
