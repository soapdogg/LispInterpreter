package edu.osu.cse6341.lispInterpreter.program;

import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.singleton.DeterminerSingleton;
import edu.osu.cse6341.lispInterpreter.singleton.EvaluatorSingleton;
import edu.osu.cse6341.lispInterpreter.singleton.PrinterSingleton;

import java.util.ArrayList;
import java.util.List;

public class Program implements IPrettyPrintable{

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
			boolean isNotList = !node.isNodeList();
			boolean isNotNumeric = !DeterminerSingleton.INSTANCE.getNumericStringDeterminer().isStringNumeric(node.getNodeValue());
			boolean isNotT = !node.getNodeValue().equals(ReservedValuesConstants.T);
			boolean isNotNil = !node.getNodeValue().equals(ReservedValuesConstants.NIL);
			if (isNotList && isNotNumeric && isNotT && isNotNil)
				throw new Exception("Error! " + node.getNodeValue() + " is not a valid atomic value!\n");
			LispNode evaluatedNode = EvaluatorSingleton.INSTANCE.getNodeEvaluator().evaluate(
				node,
				false
			);
			if(evaluatedNode != null) evaluatedNodes.add(evaluatedNode);
		}
		isEvaluated = true;
	}

	@Override
	public String getListNotationToString(boolean isFirst){
		List<LispNode> nodes = isEvaluated ? evaluatedNodes : rootNodes;
		StringBuilder sb = new StringBuilder();
		for (LispNode node : nodes) {
			sb.append(node.getListNotationToString(node.isNodeList()));
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
