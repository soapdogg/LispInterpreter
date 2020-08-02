package edu.osu.cse6341.lispInterpreter.tokenizer;

import edu.osu.cse6341.lispInterpreter.tokens.Token;
import lombok.AllArgsConstructor;

import java.util.Queue;

@AllArgsConstructor(staticName = "newInstance")
public class TokenProcessor {

    public final Queue<String> literalAtoms;
    public int numericAtomsCount, numericAtomsSum, openCount, closingCount;

    public void processTokens(Queue<Token> tokens) throws Exception {
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

    }
}
