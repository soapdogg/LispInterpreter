package edu.osu.cse6341.lispInterpreter.evaluator;

import edu.osu.cse6341.lispInterpreter.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.determiner.NumericStringDeterminer;
import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import edu.osu.cse6341.lispInterpreter.datamodels.AtomNode;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor(staticName = "newInstance")
public class ProgramEvaluator {

	private final ExpressionNodeDeterminer expressionNodeDeterminer;
	private final NumericStringDeterminer numericStringDeterminer;
	private final NodeValueComparator nodeValueComparator;
	private final NodeEvaluator nodeEvaluator;

	public List<Node> evaluate(
		List<Node> rootNodes,
		List<UserDefinedFunction> userDefinedFunctions,
		Map<String, Node> variableNameToValueMap
	) throws Exception{
		List<Node> evaluatedNodes = new ArrayList<>();
		for(Node node: rootNodes) {
			boolean isNotList = !expressionNodeDeterminer.isExpressionNode(node);
			if (isNotList) {
				AtomNode atomNode = (AtomNode)node;
				boolean isNotNumeric = !numericStringDeterminer.isStringNumeric(atomNode.getValue());
				boolean isNotT = !nodeValueComparator.equalsT(atomNode.getValue());
				boolean isNotNil = !nodeValueComparator.equalsNil(((AtomNode) node).getValue());
				if (isNotNumeric && isNotT && isNotNil)
					throw new Exception("Error! " + atomNode.getValue() + " is not a valid atomic value!\n");
			}
			Node evaluatedNode = nodeEvaluator.evaluate(
				node,
				userDefinedFunctions,
				variableNameToValueMap,
				false
			);
			evaluatedNodes.add(evaluatedNode);
		}
		return evaluatedNodes;
	}
}
