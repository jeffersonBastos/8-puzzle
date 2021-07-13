package puzzle;

import puzzle.resolvers.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Controler {
    private Scanner s = new Scanner(System.in);
        private int tabuleiroEntrada[] = {2, 3, 6, 1, 0, 5, 4, 7, 8};
//    private int tabuleiroEntrada[] = {2, 3, 6, 1, 0, 5, 4, 7, 8}; //exemplo onde o A* simples perde feio para o completo (inclusive perde para o custo uniforme)
//    private int tabuleiroEntrada[] = {1, 2, 3, 0, 5, 6, 4, 7, 8}; //exemplo onde o A* simples perde levemente para o completo
    private int tabuleiroObjetivo[] = {1, 2, 3, 4, 5, 6, 7, 8, 0};

    public Controler() {
    }

    public void start() {

        Node currentNode;

        System.out.println("Insira a sequencia do tabuleiro. Sendo o numero 0 como o espaço em branco.");
        int j = 0;


//		for (int i = 0; i < 9; i++){
//			//tabuleiroEntrada[i] = s.nextInt();
//			tabuleiroEntrada[i] = j;
//			j++;
//		}

        System.out.println("Escolha um algoritmo para buscar o caminho: \n" +
                " 1 - Custo Uniforme;\n" +
                " 2 - A* simples;\n" +
                " 3 - A* Considerando movimentos diagonais;\n" +
                " 4 - A* Com estimação minima.");

        int choosenAlgo = s.nextInt();

        PathResolver resolver = null;

        switch (choosenAlgo) {
            case 1:
                resolver = new UniformCostImpl();
                break;
            case 2:
                resolver = new AStarSimpleImpl();
                break;
            case 3:
                resolver = new AStarDiagonalImpl();
                break;
            case 4:
                resolver = new AStarMinimumEstimateImpl();
                break;
            default:
                System.out.println("Escolha invalida");
        }


        resolver
                .setInput(tabuleiroEntrada)
                .setOuput(tabuleiroObjetivo);

        this.printResults(resolver.resolvePuzzle());

    }

    private void printResults(SolutionData solutionData) {

        ArrayList<Direction> path = solutionData.getPath();

        String pathString = "{ ";

        for (int i = 0; i < path.size(); i++) {
            pathString += path.get(i).getLiteral() + " - ";
        }
        pathString += " }";

        System.out.println("Caminho para chegar ao resultado (em direções): " + pathString);
        System.out.println("Nodos visitados: " + solutionData.getNodesVisited());
        System.out.println("Nodos Criados: " + solutionData.getNodesCreated());
        System.out.println("Maior tamanho da fronteira: " + solutionData.getLargestBorderSize());
        System.out.println("Tamanho do caminho: " + solutionData.getPathSize());

    }

}

