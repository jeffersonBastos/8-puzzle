package puzzle.resolvers;

import puzzle.Node;

public class UniformCostImpl extends PathResolver {

    @Override
    public Integer calculateCost(Node node) {
        return 1;
    }

}
