package puzzle;

import java.util.Arrays;

public class Node {

	private Integer cost;
	private Node previousNode;
	private Direction generatedFromMove;
	private int[] nodeArray;

	public Node(int[] node){
		nodeArray = new int[9];
		for(int i =0; i<=8;i++){
			this.nodeArray[i] = node[i];
		}
	}

	public Node(int[] node, Node previousNode){
		this(node);
		this.previousNode = previousNode;
	}

	public Node(int[] node, Node previousNode, Direction generatedFromMove){
		this(node, previousNode);
		this.generatedFromMove = generatedFromMove;
	}

	public Node moveRight(){
		int aux = this.getEmptySquare();
		nodeArray[aux] = nodeArray[aux+1];
		nodeArray[aux+1] = 0;
		return this;
	}

	public Node moveLeft(){
		int aux = this.getEmptySquare();
		nodeArray[aux] = nodeArray[aux-1];
		nodeArray[aux-1] = 0;
		return this;
	}

	public Node moveDown(){
		int aux = this.getEmptySquare();
		nodeArray[aux] = nodeArray[aux+3];
		nodeArray[aux+3] = 0;
		return this;
	}

	public Node moveUp(){
		int aux = this.getEmptySquare();
		nodeArray[aux] = nodeArray[aux-3];
		nodeArray[aux-3] = 0;
		return this;
	}

	public int getEmptySquare(){
		for (int i=0;i<=8; i++) {
			if(nodeArray[i] == 0){
				return i;
			}
		}
		return 1;
	}

	public int[] getArray() {
		return nodeArray;
	}
	public boolean equals(Node node){
		if (!Arrays.equals(this.nodeArray, node.getArray()) ) {
			return false;
		}
		return true;
	}

	public Node getPreviousNode() {
		return this.previousNode;
	}

	public Direction getGeneratedFromMove() {
		return generatedFromMove;
	}

	public Node setCost(Integer cost) {
		this.cost = cost;
		return this;
	}

	public Integer getCost() {
		return cost;
	}
}
