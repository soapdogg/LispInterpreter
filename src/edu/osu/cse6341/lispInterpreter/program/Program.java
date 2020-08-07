package edu.osu.cse6341.lispInterpreter.program;

import edu.osu.cse6341.lispInterpreter.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.determiner.NumericStringDeterminer;
import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import edu.osu.cse6341.lispInterpreter.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor(staticName = "newInstance")
public class Program {

	private final ExpressionNodeDeterminer expressionNodeDeterminer;
	private final NumericStringDeterminer numericStringDeterminer;
	private final NodeValueComparator nodeValueComparator;
	private final NodeEvaluator nodeEvaluator;

	public List<LispNode> evaluate(
		List<LispNode> rootNodes,
		List<UserDefinedFunction> userDefinedFunctions
	) throws Exception{
		List<LispNode> evaluatedNodes = new ArrayList<>();
		for(LispNode node: rootNodes) {
			boolean isNotList = !expressionNodeDeterminer.isExpressionNode(node);
			if (isNotList) {
				AtomNode atomNode = (AtomNode)node;
				boolean isNotNumeric = !numericStringDeterminer.isStringNumeric(atomNode.getValue());
				boolean isNotT = !nodeValueComparator.equalsT(atomNode.getValue());
				boolean isNotNil = !nodeValueComparator.equalsNil(((AtomNode) node).getValue());
				if (isNotNumeric && isNotT && isNotNil)
					throw new Exception("Error! " + atomNode.getValue() + " is not a valid atomic value!\n");
			}
			LispNode evaluatedNode = nodeEvaluator.evaluate(
				node,
				userDefinedFunctions,
				false
			);
			evaluatedNodes.add(evaluatedNode);
		}
		return evaluatedNodes;
	}
}
