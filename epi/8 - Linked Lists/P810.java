/**
 * Shift a singly linked list by k.
 */
public class P810 {
	private static class Node {
		int value;
		Node next;

		Node(int value) {
			this.value = value;
		}

		Node next(int value) {
			Node node = new Node(value);
			this.next = node;
			return node;
		}

		public String toString() {
			Node curr = this;
			StringBuilder sb = new StringBuilder();
			while (curr != null) {
				sb.append(curr.value + " -> ");
				curr = curr.next;
			}
			return sb.toString();
		}
	}

	public static Node shiftLeft(Node head, int k) {
		int length = 1;
		Node tail = head;
		for (; tail.next != null; length++, tail = tail.next);
		tail.next = head;

		k %= length;

		Node newHead = head;
		for (; k > 0; k--, newHead = newHead.next);

		Node newTail = newHead;
		for (int i = 1; i < length; i++, newTail = newTail.next);
		newTail.next = null;

		return newHead;
	}

	public static Node shiftRight(Node head, int k) {
		int length = 1;
		Node tail = head;
		for (; tail.next != null; length++, tail = tail.next);
		tail.next = head;

		k %= length;

		Node newHead = tail;
		for (int i = length - k + 1; i > 0; i--, newHead = newHead.next);

		Node newTail = newHead;
		for (int i = 1; i < length; i++, newTail = newTail.next);
		newTail.next = null;

		return newHead;
	}

	public static void main(String[] args) {
		Node list = new Node(11);
		list.next(7).next(5).next(3).next(9);
		System.out.println(list);
		System.out.println(shiftRight(list, 4));
	}
}