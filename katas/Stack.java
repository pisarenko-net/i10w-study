import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {
	private Node head;
	private int count;

	public void push(Item item) {
		Node node = new Node();
		node.item = item;
		node.next = head;
		head = node;
		count++;
	}

	public Item pop() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("stack is empty");
		}

		Item item = head.item;
		head = head.next;
		count--;

		return item;
	}

	public int size() {
		return count;
	}

	public boolean isEmpty() {
		return count == 0;
	}

	private class Node {
		Item item;
		Node next;
	}

	public Iterator<Item> iterator() {
		return new StackIterator();
	}

	private class StackIterator implements Iterator<Item> {
		Node curr = head;

		public boolean hasNext() {
			return curr.next != null;
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
		Stack<String> stack = new Stack<String>();
		stack.push("Hello");
		stack.push("");
		System.out.println(stack.size());
		System.out.println(stack.isEmpty());
		System.out.println("pop(): " + stack.pop());
		System.out.println("pop(): " + stack.pop());
		System.out.println(stack.size());
		stack.pop();
	}
}
