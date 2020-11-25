package com.soapdogg.lispInterpreter.parser;

import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.ParserResult;
import com.soapdogg.lispInterpreter.datamodels.Token;
import com.soapdogg.lispInterpreter.generator.NodeGenerator;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(staticName = "newInstance")
public class AtomNodeParser {

    private final NodeGenerator nodeGenerator;
    private final ParserResultBuilder parserResultBuilder;

    public ParserResult parseAtomNode(
        final List<Token> tokens
    ) {
        final Token token = tokens.remove(0);
        final String value = token.getValue();
        final Node atomNode = nodeGenerator.generateAtomNode(value);
        return parserResultBuilder.buildParserResult(
            atomNode,
            tokens
        );
    }
}
