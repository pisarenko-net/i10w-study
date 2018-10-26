/**
 * Delete kth node from a singly linked list.
 */
public class P88 {
	private static class Node {
		Object value;
		Node next;

		Node(Object value) {
			this.value = value;
		}

		Node next(Object value) {
			Node newNode = new Node(value);
			this.next = newNode;
			return newNode;
		}
	}

	public static void deleteKthNode(Node head, int k) {
		Node curr = head;
		Node ahead = head;
		for (int i = 0; i < k; i++) ahead = ahead.next;

		while (ahead.next != null) {
			ahead = ahead.next;
			curr = curr.next;
		}

		curr.value = curr.next.value;
		curr.next = curr.next.next;
	}

	public static void printList(Node curr) {
		while (curr != null) {
			System.out.print(curr.value + " -> ");
			curr = curr.next;
		}
		System.out.println();		
	}

	public static void main(String[] args) {
		Node head = new Node("A");
		head.next("B").next("C").next("D").next("E").next("F");

		printList(head);
		deleteKthNode(head, 2);
		printList(head);
	}
}