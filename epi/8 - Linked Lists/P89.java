/**
 * Remove duplicates in a sorted singly-linked list.
 */
public class P89 {
	private static class Node {
		int value;
		Node next;

		Node(int value) {
			this.value = value;
		}

		Node next(int value) {
			Node newNode = new Node(value);
			this.next = newNode;
			return newNode;
		}
	}

	public static void removeDuplicates(Node n) {
		while (n != null && n.next != null) {
			while (n.value == n.next.value) {
				n.value = n.next.value;
				n.next = n.next.next;
			}
			n = n.next;
		}
	}

	public static void printList(Node curr) {
		while (curr != null) {
			System.out.print(curr.value + " -> ");
			curr = curr.next;
		}
		System.out.println();		
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.next(2).next(2).next(2).next(3).next(3).next(4).next(5).next(6).next(6).next(11);

		printList(head);
		removeDuplicates(head);
		printList(head);
	}
}