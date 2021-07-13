package puzzle.resolvers;

import puzzle.Node;

public class AStarMinimumEstimateImpl extends PathResolver {


    /*
     * Descrição: Para cada casa fora do lugar, apenas um ponto de custo é adicionado (estima bem para baixo)
     * Problema: A heuristica não diferencia tão bem alguns estados (pode ter situações onde só uma casa está fora do lugar, porém em uma delas seria muito mais fácil finalizar o jogo do que em outra)
     * */
    @Override
    public Integer calculateCost(Node node) {
        int[] game = node.getArray();
        int cost = 0;

        for (int i = 1; i < game.length; i++) {
            if (game[i-1] != i) {
                cost++;
            }
        }

        return cost;
    }
}
