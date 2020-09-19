package edu.osu.cse6341.lispInterpreter.evaluator;

import edu.osu.cse6341.lispInterpreter.asserter.AtomRootNodeAsserter;
import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.datamodels.AtomNode;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor(staticName = "newInstance")
public class ProgramEvaluator {

	private final ExpressionNodeDeterminer expressionNodeDeterminer;
	private final AtomRootNodeAsserter atomRootNodeAsserter;
	private final NodeEvaluator nodeEvaluator;

	public List<Node> evaluate(
		final List<Node> rootNodes,
		final List<UserDefinedFunction> userDefinedFunctions,
		final Map<String, Node> variableNameToValueMap
	) throws Exception{
		List<Node> evaluatedNodes = new ArrayList<>();
		for(Node node: rootNodes) {
			boolean isNotList = !expressionNodeDeterminer.isExpressionNode(node);
			if (isNotList) {
				AtomNode atomNode = (AtomNode)node;
				atomRootNodeAsserter.assertAtomRootNode(atomNode);
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
