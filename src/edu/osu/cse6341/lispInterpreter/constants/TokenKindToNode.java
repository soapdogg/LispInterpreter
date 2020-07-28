package edu.osu.cse6341.lispInterpreter.constants;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;

import java.util.Map;

public class TokenKindToNode {

    public static final Map<TokenKind, Node> tokenKindNodeMap = Map.of(
        TokenKind.OPEN_TOKEN, new ExpressionNode(),
        TokenKind.NUMERIC_TOKEN, new AtomNode(),
        TokenKind.LITERAL_TOKEN, new AtomNode()
    );
}
