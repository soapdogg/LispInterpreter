package edu.osu.cse6341.lispInterpreter.program.asserter;

import edu.osu.cse6341.lispInterpreter.constants.TokenKindToNode;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;

public class TokenKindAsserter {

     public void assertTokenIsAtomOrOpen(IToken token) throws Exception{
        boolean result = TokenKindToNode.tokenKindNodeMap.containsKey(token.getTokenKind());
        if (result) return;
        String errorMessage = "Expected either an ATOM or OPEN token.\nActual: " + token.getTokenKind().toString() +
            "    Value: " +
            token.toString() +
            '\n';
        throw new Exception(errorMessage);
    }
}
