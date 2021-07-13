package puzzle.resolvers;

import puzzle.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public abstract class PathResolver {

    private int tabuleiroEntrada[];
    private int tabuleiroObjetivo[];
    private ArrayList<Node> nodesVisited;
    private ArrayList<Node> border;

    public PathResolver() {
        this.nodesVisited = new ArrayList<>();
        this.border = new ArrayList<>();
    }

    public PathResolver setInput(int[] input) {
        this.tabuleiroEntrada = input;
        return this;
    }

    public PathResolver setOuput(int[] output) {
        this.tabuleiroObjetivo = output;
        return this;
    }

    public SolutionData resolvePuzzle() {
        System.out.println("Tabuleiro de inicio:");
        printTabuleiro(tabuleiroEntrada);
        System.out.println("Tabuleiro de objetivo:");
        printTabuleiro(tabuleiroObjetivo);

        boolean find;
        SolutionData solutionData = new SolutionData()
                .setPath(null)
                .setPathSize(0)
                .setLargestBorderSize(0)
                .setNodesCreated(0)
                .setNodesVisited(0);

        Node currentNode = new Node((tabuleiroEntrada));
        currentNode
                .setCost(this.calculateCost(currentNode));

        Node objectiveNode = new Node(tabuleiroObjetivo);
        Node objectiveFound = null;

        if (currentNode.equals(objectiveNode)) {
            find = true;
            System.out.println("Tabuleiro de inicio igual tabuleiro objetivo.");
        }
        find = false;

        while (!find) {
            System.out.println("visitando:");
            printTabuleiro(currentNode.getArray());

            if (currentNode.equals(objectiveNode)) {
                find = true;
                objectiveFound = currentNode;
                System.out.println("TABULEIRO OBJETIVO ENCONTRADO");
                System.out.println("Passos");
                printTabuleiro(currentNode.getArray());
            } else {
                nodesVisited.add(currentNode);
                generateNextNodes(currentNode);//adicionando em border por referência

                if (border.size() > solutionData.getLargestBorderSize())
                    solutionData.setLargestBorderSize(border.size());

                border.sort(Comparator.comparingInt(Node::getCost));

                currentNode = border.get(0);
                border.remove(0);
            }
        }

        ArrayList<Direction> path = this.materializePath(objectiveNode, objectiveFound);

        return solutionData
                .setPath(path)
                .setPathSize(path.size())
                .setNodesCreated(nodesVisited.size() + border.size())
                .setNodesVisited(nodesVisited.size());
    }

    private ArrayList<Direction> materializePath(Node objectiveNode, Node objectiveFound) {
        ArrayList<Direction> path = new ArrayList();

        while (objectiveFound.getPreviousNode() != null) {
            path.add(0, objectiveFound.getGeneratedFromMove());

            objectiveFound = objectiveFound.getPreviousNode();
        }

        return path;
    }


    private void generateNextNodes(Node currentNode) {
        int emptySquare = 0;
        Node right = new Node(currentNode.getArray(), currentNode, Direction.right);
        Node down = new Node(currentNode.getArray(), currentNode, Direction.down);
        Node left = new Node(currentNode.getArray(), currentNode, Direction.left);
        Node up = new Node(currentNode.getArray(), currentNode, Direction.up);

        emptySquare = currentNode.getEmptySquare();
        switch (emptySquare) {
            case 0:
                right.moveRight()
                        .setCost(this.calculateCost(right));
                down.moveDown()
                        .setCost(this.calculateCost(down));
                this.addNodeToBorder(right);
                this.addNodeToBorder(down);
                break;
            case 1:
                right.moveRight()
                        .setCost(this.calculateCost(right));
                down.moveDown()
                        .setCost(this.calculateCost(down));
                left.moveLeft()
                        .setCost(this.calculateCost(left));
                this.addNodeToBorder(left);
                this.addNodeToBorder(right);
                this.addNodeToBorder(down);
                break;
            case 2:
                down.moveDown()
                        .setCost(this.calculateCost(down));
                left.moveLeft()
                        .setCost(this.calculateCost(left));
                this.addNodeToBorder(down);
                this.addNodeToBorder(left);
                break;
            case 3:
                right.moveRight()
                        .setCost(this.calculateCost(right));
                down.moveDown()
                        .setCost(this.calculateCost(down));
                up.moveUp()
                        .setCost(this.calculateCost(up));
                this.addNodeToBorder(right);
                this.addNodeToBorder(down);
                this.addNodeToBorder(up);
                break;
            case 4:
                right.moveRight()
                        .setCost(this.calculateCost(right));
                down.moveDown()
                        .setCost(this.calculateCost(down));
                left.moveLeft()
                        .setCost(this.calculateCost(left));
                up.moveUp()
                        .setCost(this.calculateCost(up));
                this.addNodeToBorder(right);
                this.addNodeToBorder(down);
                this.addNodeToBorder(left);
                this.addNodeToBorder(up);
                break;
            case 5:
                down.moveDown()
                        .setCost(this.calculateCost(down));
                left.moveLeft()
                        .setCost(this.calculateCost(left));
                up.moveUp()
                        .setCost(this.calculateCost(up));
                this.addNodeToBorder(down);
                this.addNodeToBorder(left);
                this.addNodeToBorder(up);
                break;
            case 6:
                right.moveRight()
                        .setCost(this.calculateCost(right));
                up.moveUp()
                        .setCost(this.calculateCost(up));
                this.addNodeToBorder(right);
                this.addNodeToBorder(up);
                break;
            case 7:
                right.moveRight()
                        .setCost(this.calculateCost(right));
                left.moveLeft()
                        .setCost(this.calculateCost(left));
                up.moveUp()
                        .setCost(this.calculateCost(up));
                this.addNodeToBorder(right);
                this.addNodeToBorder(left);
                this.addNodeToBorder(up);
                break;
            case 8:
                left.moveLeft()
                        .setCost(this.calculateCost(left));
                up.moveUp()
                        .setCost(this.calculateCost(up));
                this.addNodeToBorder(left);
                this.addNodeToBorder(up);
                break;
        }

    }

    //poderia receber o nodo objetivo tbm
    public abstract Integer calculateCost(Node node);

    private void addNodeToBorder(Node node) {
        if (!isInList(node.getArray())) {
            border.add(node);
        }
    }

    private boolean isInList(int[] currentNode) {//hash melhoraria performance
        for (Node item : nodesVisited) {
            if (!Arrays.equals(item.getArray(), currentNode)) {
                continue;
            }
            return true;
        }
        return false;
    }

    private void printTabuleiro(int[] tabuleiro) {
        for (int i = 0; i < 9; i += 3) {
            System.out.print("|" + tabuleiro[i] + "|" + tabuleiro[i + 1] + "|" + tabuleiro[i + 2] + "|\n");
        }
    }

}
