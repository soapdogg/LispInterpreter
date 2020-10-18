package com.soapdogg.lispInterpreter.parser;

import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.ParserResult;
import com.soapdogg.lispInterpreter.datamodels.Token;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@AllArgsConstructor(staticName = "newInstance")
public class RootParser {

    private final NodeParser nodeParser;

    public List<Node> parse(Queue<Token> tokens) throws Exception {

        List<Node> rootNodes = new ArrayList<>();
        while (!tokens.isEmpty()) {
            ParserResult root = nodeParser.parseIntoNode(tokens);
            rootNodes.add(root.getResultingNode());
            tokens = root.getRemainingTokens();
        }
        return rootNodes;
    }
}
