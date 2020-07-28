package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.program.IEvaluatable;
import edu.osu.cse6341.lispInterpreter.program.IParsable;
import edu.osu.cse6341.lispInterpreter.program.IPrettyPrintable;
import edu.osu.cse6341.lispInterpreter.program.parser.Parser;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;

public class StartNode implements IParsable, IEvaluatable, IPrettyPrintable{
    private Node node;
	private StartNode nextExpressionStartNode;

	private final Parser parser;

    public StartNode(){
        parser = new Parser();
    }

    @Override
    public void parse(Tokenizer tokenizer) throws Exception{
        if(tokenizer.getCurrent().getTokenKind() == TokenKind.EOF_TOKEN) return;
        node = (Node)parser.parseIntoNode(tokenizer);
		if(tokenizer.getCurrent().getTokenKind() == TokenKind.EOF_TOKEN) return;
		nextExpressionStartNode = new StartNode();
		nextExpressionStartNode.parse(tokenizer);
    }

	@Override
	public Node evaluate(boolean areLiteralsAllowed) throws Exception{
        boolean isNotList = !((LispNode)node).isNodeList();
        boolean isNotNumeric = !((LispNode)node).isNodeNumeric();
        boolean isNotT = !((LispNode)node).getNodeValue().equals(ReservedValuesConstants.T);
        boolean isNotNil = !((LispNode)node).getNodeValue().equals(ReservedValuesConstants.NIL);
        if(isNotList && isNotNumeric && isNotT && isNotNil) throw new Exception("Error! " + ((LispNode)node).getNodeValue() + " is not a valid atomic value!\n");
        node = node.evaluate(false);
		if(nextExpressionStartNode != null) nextExpressionStartNode.evaluate(true);
		return null;
	}

	@Override
    public String getListNotationToString(boolean isFirst){
		StringBuilder sb = new StringBuilder();
		if(node != null) {
		    sb.append(node.getListNotationToString(((LispNode)node).isNodeList()));
		    sb.append('\n');
		}
		if(nextExpressionStartNode != null) sb.append(nextExpressionStartNode.getListNotationToString(isFirst));
		return sb.toString();
    }

    @Override
    public String getDotNotationToString(){
        if(node == null) return "NIL\n";
        StringBuilder result = new StringBuilder(node.getDotNotationToString());
        result.append('\n');
        if(nextExpressionStartNode != null) result.append(nextExpressionStartNode.getDotNotationToString());
        return result.toString();
    }

}
