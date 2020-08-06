package edu.osu.cse6341.lispInterpreter.datamodels;

import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(staticName = "newInstance")
public class PartitionedRootNodes {

    private final List<LispNode> defunNodes;
    private final List<LispNode> evaluatableNodes;
}
