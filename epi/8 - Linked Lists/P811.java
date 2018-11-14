/**
 * Put even elements first, followed by the odd elements.
 */
public class P811 {
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

	public static Node mergeOddEven(Node head) {
		Node even = new Node(-1), odd = new Node(-1);

		Node curr = head, currEven = even, currOdd = odd;
		boolean isOdd = false;

		while (curr != null) {
			if (isOdd) {
				currOdd.next = curr;
				currOdd = currOdd.next;
			} else {
				currEven.next = curr;
				currEven = currEven.next;
			}

			curr = curr.next;
			isOdd = !isOdd;
		}

		currEven.next = odd;
		currOdd.next = null;
		return even.next;
	}

	public static void main(String[] args) {
		Node list = new Node(2);
		list.next(3).next(4).next(5).next(6).next(9).next(24).next(13).next(200);
		System.out.println(mergeOddEven(list));
	}
}