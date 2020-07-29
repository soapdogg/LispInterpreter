package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.program.IEvaluatable;
import edu.osu.cse6341.lispInterpreter.program.IPrettyPrintable;
import edu.osu.cse6341.lispInterpreter.program.parser.Parser;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;

public class StartNode implements IEvaluatable, IPrettyPrintable{
    private LispNode node;
	private StartNode nextExpressionStartNode;

	private final Parser parser;

    public StartNode(){
        parser = new Parser();
    }

    public void parse(Tokenizer tokenizer) throws Exception{
        if(tokenizer.getCurrent().getTokenKind() == TokenKind.EOF_TOKEN) return;
        node = parser.parseIntoNode(tokenizer);
		if(tokenizer.getCurrent().getTokenKind() == TokenKind.EOF_TOKEN) return;
		nextExpressionStartNode = new StartNode();
		nextExpressionStartNode.parse(tokenizer);
    }

	@Override
	public LispNode evaluate(boolean areLiteralsAllowed) throws Exception{
        boolean isNotList = !node.isNodeList();
        boolean isNotNumeric = !node.isNodeNumeric();
        boolean isNotT = !node.getNodeValue().equals(ReservedValuesConstants.T);
        boolean isNotNil = !node.getNodeValue().equals(ReservedValuesConstants.NIL);
        if(isNotList && isNotNumeric && isNotT && isNotNil) throw new Exception("Error! " + node.getNodeValue() + " is not a valid atomic value!\n");
        node = ((IEvaluatable)node).evaluate(false);
		if(nextExpressionStartNode != null) nextExpressionStartNode.evaluate(true);
		return null;
	}

	@Override
    public String getListNotationToString(boolean isFirst){
		StringBuilder sb = new StringBuilder();
		if(node != null) {
		    sb.append(((IPrettyPrintable)node).getListNotationToString(node.isNodeList()));
		    sb.append('\n');
		}
		if(nextExpressionStartNode != null) sb.append(nextExpressionStartNode.getListNotationToString(isFirst));
		return sb.toString();
    }

    @Override
    public String getDotNotationToString(){
        if(node == null) return "NIL\n";
        StringBuilder result = new StringBuilder(((IPrettyPrintable)node).getDotNotationToString());
        result.append('\n');
        if(nextExpressionStartNode != null) result.append(nextExpressionStartNode.getDotNotationToString());
        return result.toString();
    }

}
