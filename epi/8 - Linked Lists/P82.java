import lombok.*;

/**
 * Reverse a singly linked list without using extra memory.
 */
public class P82 {
	@AllArgsConstructor
	private static class List<T> {
		T value;
		List next;
	}

	public static <T> List<T> reverse(List<T> list) {
		List<T> curr = list, prev = null;

		while (curr != null) {
			List<T> temp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = temp;
		}

		return prev;
	}

	public static void main(String[] args) {
		List<Integer> inputList = createLinkedList(new int[]{2, 5, 6, 9});
		printLinkedList(inputList);

		printLinkedList(reverse(inputList));
	}

	private static List<Integer> createLinkedList(int[] a) {
		int i = 1;
		List head = new List(a[0], null);
		List curr = head;

		do {
			List node = new List(a[i], null);
			curr.next = node;
			curr = node;
			i++;
		} while (i < a.length);

		return head;
	}

	private static <T extends Comparable<T>> void printLinkedList(List<T> list) {
		while (list != null) {
			System.out.println(list.value);
			list = list.next;
		}
	}
}