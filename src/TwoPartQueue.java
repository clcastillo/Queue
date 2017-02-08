
public class TwoPartQueue<T> implements QueueInterface<T> {
	private Node queueNode;
	private Node freeNode;

	public TwoPartQueue() {
		freeNode = new Node(null, null);
		freeNode.setNextNode(freeNode);
		queueNode = freeNode;
	}

	private class Node {
		private T data;
		private Node next;

		private Node(T dataPortion) {
			this(dataPortion, null);
		}

		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		}

		public T getData() {
			return data;

		}

		public void setData(T data) {
			this.data = data;
		}

		public Node getNextNode() {
			return next;
		}

		public void setNextNode(Node nextNode) {
			next = nextNode;
		}
	}

	@Override
	public void enqueue(T newEntry) {
		freeNode.setData(newEntry);
		if (isChainFull()) {
			Node newNode = new Node(null, freeNode.getNextNode());
			freeNode.setNextNode(newNode);
		}
		freeNode = freeNode.getNextNode();
	}

	@Override
	public T dequeue() {
		T front = null;
		if (!isEmpty()) {
			front = queueNode.getData();
			queueNode.setData(null);
			queueNode = queueNode.getNextNode();
		}
		return front;
	}

	@Override
	public T getFront() {
		T front = null;
		if (!isEmpty())
			front = queueNode.getData();
		return front;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return queueNode == freeNode;
	}

	private boolean isChainFull() {
		return queueNode == freeNode.getNextNode();
	}

	@Override
	public void clear() {
		if (queueNode != null)
			dequeue();
	}

}
