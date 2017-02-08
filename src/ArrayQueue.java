
public class ArrayQueue<T> implements QueueInterface<T> {
	private T[] queue;
	private int frontIndex;
	private int backIndex;
	private static final int DEFAULT_INITIAL_CAPACITY = 10;

	public ArrayQueue() {
		@SuppressWarnings("unchecked")

		T[] tempQueue = (T[]) new Object[DEFAULT_INITIAL_CAPACITY - 1];
		queue = tempQueue;
		frontIndex = 0;
		backIndex = DEFAULT_INITIAL_CAPACITY;

	} // end default constructor

	@Override
	public void enqueue(T newEntry) {
		// TODO Auto-generated method stub
		ensureCapacity();
		backIndex = (backIndex + 1) % queue.length;
		queue[backIndex] = newEntry;
	}

	private void ensureCapacity() {
		if (frontIndex == ((backIndex + 2) % queue.length)) {
			T[] oldQueue = queue;
			int oldSize = oldQueue.length;
			@SuppressWarnings("unchecked")
			T[] tempQueue = (T[]) new Object[2 + oldSize];
			queue = tempQueue;
			for (int i = 0; i < oldSize - 1; i++) {
				queue[i] = oldQueue[frontIndex];
				frontIndex = (frontIndex + 1) % oldSize;
			}
			frontIndex = 0;
			backIndex = oldSize - 2;
		}
	}

	@Override
	public T dequeue() {
		T front = null;
		if (!isEmpty()) {
			front = queue[frontIndex];
			queue[frontIndex] = null;
			frontIndex = (frontIndex + 1) % queue.length;
		}
		return front;
	}

	@Override
	public T getFront() {
		T front = null;
		if (!isEmpty())
			front = queue[frontIndex];
		return front;
	}

	@Override
	public boolean isEmpty() {
		return frontIndex == ((backIndex + 1) % queue.length);
	}

	@Override
	// Come back to this one
	public void clear() {
		if (!isEmpty()) {
			for (int i = 0; i < queue.length - 1; i++) {
				dequeue();
			}
		}
	}

}
