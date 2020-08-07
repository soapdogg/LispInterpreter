package edu.osu.cse6341.lispInterpreter.datamodels;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(staticName = "newInstance")
public class PartitionedRootNodes {

    private final List<Node> defunNodes;
    private final List<Node> evaluatableNodes;
}
