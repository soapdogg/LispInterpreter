package edu.osu.cse6341.lispInterpreter.program;

import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.singleton.DeterminerSingleton;
import edu.osu.cse6341.lispInterpreter.singleton.EvaluatorSingleton;
import edu.osu.cse6341.lispInterpreter.singleton.PrinterSingleton;

import java.util.ArrayList;
import java.util.List;

public class Program {

	private List<LispNode> rootNodes;
	private List<LispNode> evaluatedNodes;
	private boolean isEvaluated;

	public Program(
		List<LispNode> rootNodes
	){
		this.rootNodes = rootNodes;
        evaluatedNodes = new ArrayList<>();
        isEvaluated = false;
	}

	public void evaluate() throws Exception{
		for(LispNode node: rootNodes) {
			boolean isNotList = !DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer().isExpressionNode(node);
			if (isNotList) {
				AtomNode atomNode = (AtomNode)node;
				boolean isNotNumeric = !DeterminerSingleton.INSTANCE.getNumericStringDeterminer().isStringNumeric(atomNode.getValue());
				boolean isNotT = !atomNode.getValue().equals(ReservedValuesConstants.T);
				boolean isNotNil = !atomNode.getValue().equals(ReservedValuesConstants.NIL);
				if (isNotNumeric && isNotT && isNotNil)
					throw new Exception("Error! " + atomNode.getValue() + " is not a valid atomic value!\n");
			}
			LispNode evaluatedNode = EvaluatorSingleton.INSTANCE.getNodeEvaluator().evaluate(
				node,
				false
			);
			if(evaluatedNode != null) evaluatedNodes.add(evaluatedNode);
		}
		isEvaluated = true;
	}

	public String getListNotationToString(){
		List<LispNode> nodes = isEvaluated ? evaluatedNodes : rootNodes;
		StringBuilder sb = new StringBuilder();
		for (LispNode node : nodes) {
			String listNotation = PrinterSingleton.INSTANCE.getListNotationPrinter().printInListNotation(
				node
			);
			sb.append(listNotation);
			sb.append('\n');
		}
	    return sb.toString();
    }

    public String getDotNotationToString(){
		List<LispNode> nodes = isEvaluated ? evaluatedNodes : rootNodes;
		if (nodes.isEmpty()) return "NIL\n";
		StringBuilder sb = new StringBuilder();
		for (LispNode node : nodes) {
			String dotNotation = PrinterSingleton.INSTANCE.getDotNotationPrinter().printInDotNotation(node);
			sb.append(dotNotation);
			sb.append('\n');
		}
		return sb.toString();
	}

}
