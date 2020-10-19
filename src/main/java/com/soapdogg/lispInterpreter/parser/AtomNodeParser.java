package com.soapdogg.lispInterpreter.parser;

import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.ParserResult;
import com.soapdogg.lispInterpreter.datamodels.Token;
import com.soapdogg.lispInterpreter.generator.NodeGenerator;
import lombok.AllArgsConstructor;

import java.util.Queue;

@AllArgsConstructor(staticName = "newInstance")
public class AtomNodeParser {

    private final NodeGenerator nodeGenerator;

    public ParserResult parseAtomNode(
        final Queue<Token> tokens
    ) {
        final Token token = tokens.remove();
        final String value = token.getValue();
        final Node atomNode = nodeGenerator.generateAtomNode(value);
        return ParserResult.newInstance(
            atomNode,
            tokens
        );
    }
}
