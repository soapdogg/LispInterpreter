package com.soapdogg.lispInterpreter.interpreter;

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants;
import com.soapdogg.lispInterpreter.datamodels.PartitionedRootNodes;
import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor(staticName = "newInstance")
public class RootNodePartitioner {

    public PartitionedRootNodes partitionRootNodes(
        List<Node> rootNodes
    ) {
        Map<Boolean, List<Node>> defunAndExecutables = rootNodes.stream()
            .collect(
                Collectors.partitioningBy(
                    rootNode -> {
                        boolean isRootNodeExpressionNode = rootNode instanceof ExpressionNode;
                        if (isRootNodeExpressionNode) {
                            ExpressionNode expressionRootNode = (ExpressionNode)rootNode;
                            Node rootNodeAddress = expressionRootNode.getAddress();
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
        return new PartitionedRootNodes(
            defunAndExecutables.get(true),
            defunAndExecutables.get(false)
        );
    }
}
