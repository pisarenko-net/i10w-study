/**
 * Find a cycle in a linked list and return the first node from the cycle.
 */
public class P84 {
	private static class Node<T> {
		T value;
		Node next;

		public Node(T value) {
			this.value = value;
		}
	}

	public static <T> Node<T> findCycle(Node<T> head) {
		Node<T> fast = head;
		Node<T> slow = head;

		while (slow != null && fast != null && slow.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;

			if (slow == fast) {
				int cycleLength = 0;
				do {
					fast = fast.next;
					cycleLength++;
				} while (slow != fast);

				slow = head;
				fast = head;
				while (cycleLength > 0) {
					fast = fast.next;
					cycleLength--;
				}

				while (fast != slow) {
					fast = fast.next;
					slow = slow.next;
				}

				return slow;
			}
		}

		return null;
	}

	public static void main(String[] args) {
		Node<Character> a = new Node('a');
		Node<Character> e = new Node('e');
		Node<Character> s = new Node('s');
		Node<Character> g = new Node('g');

		a.next = e;
		e.next = s;
		s.next = g;
		g.next = e;

		System.out.println(findCycle(a).value);
	}
}