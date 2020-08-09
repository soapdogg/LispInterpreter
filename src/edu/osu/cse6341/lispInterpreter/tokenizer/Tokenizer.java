package edu.osu.cse6341.lispInterpreter.tokenizer;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

import edu.osu.cse6341.lispInterpreter.asserter.LineFormatAsserter;
import edu.osu.cse6341.lispInterpreter.datamodels.Token;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class Tokenizer {

    private final ScannerToLineTransformer scannerToLineTransformer;
    private final LineFormatAsserter lineFormatAsserter;
    private final LineTokenizer lineTokenizer;

	public Queue<Token> tokenize(Scanner in) throws Exception {
		Queue<Token> tokens = new LinkedList<>();
        List<String> lines = scannerToLineTransformer.transformScannerInputToLines(
            in
        );
        for (String line : lines) {
            lineFormatAsserter.assertLineFormat(line);
            Queue<Token> tokenizedLineResult = lineTokenizer.tokenizeLine(
                line
            );
            tokens.addAll(tokenizedLineResult);
        }
        return tokens;
	}
}
