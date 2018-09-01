import lombok.*;

/**
 * Merge two sorted singly-linked lists.
 */
public class P81 {
	@AllArgsConstructor
	private static class Node<T extends Comparable<T>> {
		T value;
		Node next;
	}

	public static <T extends Comparable<T>> Node<T> merge(Node<T> a, Node<T> b) {
		Node head = new Node(null, null);
		Node curr = head;

		while (a != null && b != null) {
			if (a.value.compareTo(b.value) <= 0) {
				curr.next = a;
				a = a.next;
			} else {
				curr.next = b;
				b = b.next;
			}

			curr = curr.next;
		}

		curr.next = (a == null) ? b : a;
		return head.next;
	}

	public static void main(String[] args) {
		int[] array1 = new int[]{2, 4, 6, 24};
		int[] array2 = new int[]{1, 12, 13, 70};

		Node<Integer> list1 = createLinkedList(array1);
		Node<Integer> list2 = createLinkedList(array2);
		printLinkedList(merge(list1, list2));
	}

	private static Node<Integer> createLinkedList(int[] a) {
		int i = 1;
		Node head = new Node(a[0], null);
		Node curr = head;

		do {
			Node node = new Node(a[i], null);
			curr.next = node;
			curr = node;
			i++;
		} while (i < a.length);

		return head;
	}

	private static <T extends Comparable<T>> void printLinkedList(Node<T> list) {
		while (list != null) {
			System.out.println(list.value);
			list = list.next;
		}
	}
}