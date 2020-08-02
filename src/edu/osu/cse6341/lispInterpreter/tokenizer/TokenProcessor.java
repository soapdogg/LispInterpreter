package edu.osu.cse6341.lispInterpreter.tokenizer;

import edu.osu.cse6341.lispInterpreter.datamodels.ProcessedTokensResult;
import edu.osu.cse6341.lispInterpreter.tokens.Token;
import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.Queue;

@AllArgsConstructor(staticName = "newInstance")
public class TokenProcessor {


    public ProcessedTokensResult processTokens(Queue<Token> tokens) throws Exception {
        Queue<String> literalAtoms = new LinkedList<>();
        int openCount = 0;
        int closingCount = 0;
        int numericAtomsSum = 0;
        int numericAtomsCount = 0;

        for(Token token: tokens) {
            switch (token.getTokenKind()) {
                case OPEN_TOKEN -> ++openCount;
                case CLOSE_TOKEN -> ++closingCount;
                case LITERAL_TOKEN -> literalAtoms.add(token.getValue());
                case NUMERIC_TOKEN -> {
                    ++numericAtomsCount;
                    numericAtomsSum += Integer.parseInt(token.getValue());
                }
                case ERROR_TOKEN -> {
                    String errorMessage = "Error! Invalid token: " + token.getValue() + "\n";
                    throw new Exception(errorMessage);
                }
                default -> {}
            }
        }
        return ProcessedTokensResult.newInstance(
            literalAtoms,
            openCount,
            closingCount,
            numericAtomsSum,
            numericAtomsCount
        );
    }
}
