package puzzle;

public class QueueNext {
	private NodeQueue first; // mais recente
	private NodeQueue last;  // mais antigo
	private int N;

	public QueueNext(){}

	private class NodeQueue {
		private Node node;
		private NodeQueue next;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return N;
	}

	public void addNode(Node node) {
		NodeQueue oldlast = last;
		last = new NodeQueue();
		last.node = node;
		last.next = null;
		if (isEmpty()) first = last;
		else oldlast.next = last;
		N++;
	}

	public Node getNext() {
		Node node = first.node;
		first = first.next;
		N--;
		if (isEmpty()) last = null;
		return node;
	}

}
