import lombok.*;

/**
 * Reverse a sublist of a singly-linked list.
 */
public class P83 {
	@AllArgsConstructor
	private static class List<T> {
		T value;
		List next;
	}

	public static <T> List<T> reverseSublist(List<T> list, int start, int finish) {
		if (start == finish) return list;

		List<T> dummyHead = new List(null, list);
		List<T> sublistHead = dummyHead.next;
		int k = 1;
		while (k++ < start) sublistHead = sublistHead.next;

		List<T> sublistTail = sublistHead.next;
		while (start++ < finish) {
			List<T> temp = sublistTail.next;
			sublistTail.next = temp.next;
			temp.next = sublistHead.next;
			sublistHead.next = temp;
		}

		return dummyHead.next;
	}

	public static void main(String[] args) {
		List<Integer> inputList = createLinkedList(new int[]{11, 7, 5, 3, 2});
		printLinkedList(inputList);
		printLinkedList(reverseSublist(inputList, 2, 4));
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