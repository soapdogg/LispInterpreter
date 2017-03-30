package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.program.IEvaluatable;
import edu.osu.cse6341.lispInterpreter.program.IParsable;
import edu.osu.cse6341.lispInterpreter.program.IPrettyPrintable;
import edu.osu.cse6341.lispInterpreter.program.ITypeCheckable;
import edu.osu.cse6341.lispInterpreter.program.types.IType;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;

public class StartNode implements IParsable, IEvaluatable, IPrettyPrintable, ITypeCheckable{
    private Node node;
	private StartNode nextExpressionStartNode;

    public StartNode(){
    }

    @Override
    public void parse(Tokenizer tokenizer) throws Exception{
        if(tokenizer.getCurrent().getTokenKind() == TokenKind.EOF_TOKEN) return;
        node = Node.parseIntoNode(tokenizer);
		if(tokenizer.getCurrent().getTokenKind() == TokenKind.EOF_TOKEN) return;
		nextExpressionStartNode = new StartNode();
		nextExpressionStartNode.parse(tokenizer);
    }

    @Override
    public IType typeCheck(boolean areLiteralsAllowed) throws Exception{
        boolean isNotList = !node.isList();
        boolean isNotNumeric = !node.isNumeric();
        boolean isNotT = !Node.equalsT(node.getValue());
        boolean isNotNil = !Node.equalsNil(node.getValue());
        boolean isNotF = !Node.equalsF(node.getValue());
        if(isNotList && isNotNumeric && isNotT && isNotNil && isNotF) throw new Exception("Error! " + node.getValue() + " is not a valid atomic value!\n");
        IType type  = node.typeCheck(areLiteralsAllowed);
        System.out.println(type.toString());
        if(nextExpressionStartNode != null) nextExpressionStartNode.typeCheck(areLiteralsAllowed);
        return null;
    }

	@Override
	public Node evaluate(boolean areLiteralsAllowed) throws Exception{
        boolean isNotList = !node.isList();
        boolean isNotNumeric = !node.isNumeric();
        boolean isNotT = !node.getValue().equals("T");
        boolean isNotNil = !node.getValue().equals("NIL");
        boolean isNotF = !Node.equalsF(node.getValue());
        if(isNotList && isNotNumeric && isNotT && isNotNil && isNotF) throw new Exception("Error! " + node.getValue() + " is not a valid atomic value!\n");
        node = node.evaluate(false);
		if(nextExpressionStartNode != null) nextExpressionStartNode.evaluate(true);
		return null;
	}

	@Override
    public String getListNotationToString(boolean isFirst){
		StringBuilder sb = new StringBuilder();
		if(node != null) {
		    sb.append(node.getListNotationToString(node.isList()));
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

    @Override
    public String getTypeToString() {
        StringBuilder sb = new StringBuilder();
        if(node != null){
            sb.append(node.getTypeToString());
            sb.append('\n');
        }
        if(nextExpressionStartNode != null) sb.append(nextExpressionStartNode.getTypeToString());
        return sb.toString();
    }

}
