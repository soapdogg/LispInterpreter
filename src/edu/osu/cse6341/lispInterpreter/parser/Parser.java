package edu.osu.cse6341.lispInterpreter.parser;

import edu.osu.cse6341.lispInterpreter.asserter.TokenKindAsserter;
import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.generator.NodeGenerator;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokens.Token;
import edu.osu.cse6341.lispInterpreter.tokens.TokenKind;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(staticName = "newInstance")
public class Parser {

    private final TokenKindAsserter tokenKindAsserter;
    private final NodeGenerator nodeGenerator;

    public List<LispNode> parse(Tokenizer tokenizer) throws Exception {

        List<LispNode> rootNodes = new ArrayList<>();
        while (tokenizer.getCurrent().getTokenKind() != TokenKind.EOF_TOKEN) {
            LispNode root = parseIntoNode(tokenizer);
            rootNodes.add(root);
        }
        return rootNodes;
    }

    public LispNode parseIntoNode(
        Tokenizer tokenizer
    ) throws Exception {
        Token token = tokenizer.getCurrent();
        tokenKindAsserter.assertTokenIsAtomOrOpen(token);
        TokenKind currentTokenKind = token.getTokenKind();
        boolean isOpen = currentTokenKind == TokenKind.OPEN_TOKEN;
        if (isOpen) {
            tokenizer.getNextToken();
            LispNode result = parseExpressionNode(tokenizer);
            tokenizer.getNextToken();
            return result;
        } else {
            token = tokenizer.getNextToken();
            String value = token.getValue();
            return nodeGenerator.generateAtomNode(
                value
            );
        }
    }

    private LispNode parseExpressionNode(
        Tokenizer tokenizer
    ) throws Exception {
        boolean isClose = tokenizer.getCurrent().getTokenKind() == TokenKind.CLOSE_TOKEN;
        LispNode result;
        if (isClose) result = nodeGenerator.generateAtomNode(ReservedValuesConstants.NIL);//nodeGenerator.generateExpressionNode();
        else {
            LispNode address = parseIntoNode(tokenizer);
            LispNode data = parseExpressionNode(tokenizer);
            result = nodeGenerator.generateExpressionNode(
                address,
                data
            );
        }
        return result;
    }

}
