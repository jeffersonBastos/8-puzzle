package puzzle.resolvers;

import puzzle.Node;

public class AStarDiagonalImpl extends PathResolver {

    /*
     * Descrição: Distância de cada casa de sua posição atual até a sua posição destino (considerando jogadas na vertical e transversal)
     * Problema: Pode considerar uma jogada na vertical com mesmo valor de uma jogada direta
     * */
    @Override
    public Integer calculateCost(Node node) {
        int[] array = node.getArray();
        int cost = 0;

        for (int i = 1; i < array.length; i++) {
            int distancia = Math.abs(array[i - 1] - i);

            if (distancia != 0)
                cost += 3 % distancia;
        }

        return cost;
    }
}
