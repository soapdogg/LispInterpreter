package edu.osu.cse6341.lispInterpreter.program.asserter;

import edu.osu.cse6341.lispInterpreter.constants.TokenKindToNode;
import edu.osu.cse6341.lispInterpreter.exceptions.UnexpectedTokenKindException;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class TokenKindAsserter {

     public void assertTokenIsAtomOrOpen(
         final IToken token
     ) throws Exception{
        boolean result = TokenKindToNode.tokenKindNodeMap.containsKey(token.getTokenKind());
        if (result) return;
        String errorMessage = "Expected either an ATOM or OPEN token.\nActual: " + token.getTokenKind().toString() +
            "    Value: " +
            token.toString() +
            '\n';
        throw new UnexpectedTokenKindException(errorMessage);
    }
}
