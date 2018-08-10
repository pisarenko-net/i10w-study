import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {
	private int size;
	private Node head;
	private Node tail;

	public void enqueue(Item item) {
		Node node = new Node();
		node.item = item;

		if (isEmpty()) {
			head = tail = node;
		} else {
			tail.next = node;
			tail = node;
		}

		size++;
	}

	public Item dequeue() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("queue is empty");
		}

		Item item = head.item;
		if (size == 1) {
			head = tail = null;
		} else {
			head = head.next;
		}
		size--;
		return item;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public Iterator<Item> iterator() {
		return new QueueIterator();
	}

	private class Node {
		Item item;
		Node next;
	}

	private class QueueIterator implements Iterator<Item> {
		Node curr = head;

		public boolean hasNext() {
			return curr != null && curr.next != null;
		}

		public Item next() {
			Item item = curr.item;
			curr = curr.next;
			return item;
		}

		public void remove() {
		}
	}

	public static void main(String[] args) {
		Queue<Integer> queue = new Queue<>();

		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);

		System.out.print("Current state of the queue: ");
		for (Integer item : queue) {
			System.out.print(item + " ");
		}
		System.out.println();

		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());

		queue.enqueue(5);

		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());

		System.out.println(queue.dequeue());
	}
}