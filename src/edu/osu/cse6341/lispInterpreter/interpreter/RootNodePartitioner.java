package edu.osu.cse6341.lispInterpreter.interpreter;

import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.PartitionedRootNodes;
import edu.osu.cse6341.lispInterpreter.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor(staticName = "newInstance")
public class RootNodePartitioner {

    public PartitionedRootNodes partitionRootNodes(
        List<LispNode> rootNodes
    ) {
        Map<Boolean, List<LispNode>> defunAndExecutables = rootNodes.stream()
            .collect(
                Collectors.partitioningBy(
                    rootNode -> {
                        boolean isRootNodeExpressionNode = rootNode instanceof ExpressionNode;
                        if (isRootNodeExpressionNode) {
                            ExpressionNode expressionRootNode = (ExpressionNode)rootNode;
                            LispNode rootNodeAddress = expressionRootNode.getAddress();
                            boolean isRootNodeAddressAtom = rootNodeAddress instanceof AtomNode;
                            if (isRootNodeAddressAtom) {
                               AtomNode rootNodeAddressAtom = (AtomNode)rootNodeAddress;
                                return rootNodeAddressAtom.getValue().equals(FunctionNameConstants.DEFUN);
                            }
                        }
                        return false;
                    }
                )
            );
        return PartitionedRootNodes.newInstance(
            defunAndExecutables.get(true),
            defunAndExecutables.get(false)
        );
    }
}
