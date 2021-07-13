package puzzle.resolvers;

import puzzle.Node;

public class AStarSimpleImpl extends PathResolver {

    /*
    * Descrição: Distância de cada casa de sua posição atual até a sua posição destino
    * Problema: (Não considera jogadas na vertical, então o custo de mover da posição 6 para a 9 é 3 (poderia ser estimado para 0))
    * */
    @Override
    public Integer calculateCost(Node node) {
        int[] array = node.getArray();
        int cost = 0;

        for (int i = 1; i < array.length; i++) {
            cost += Math.abs(array[i-1] - i);
        }

        return cost;
    }
}
