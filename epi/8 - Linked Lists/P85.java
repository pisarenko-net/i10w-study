/**
 * Find first common node in two singly-linked lists.
 */
public class P85 {
	private static class Node<T extends Comparable<T>> {
		T value;
		Node<T> next;

		Node(T value) {
			this.value = value;
		}
	}

	public static <T extends Comparable<T>> Node<T> findFirstCommonNode(Node<T> a, Node<T> b) {
		int aLength = length(a);
		int bLength = length(b);

		Node<T> longerList = aLength > bLength ? a : b;
		Node<T> otherList = aLength > bLength ? b : a;
		int delta = Math.abs(aLength - bLength);

		for (int i = 0; i < delta; i++) longerList = longerList.next;

		while (longerList != null && otherList != null && otherList != longerList) {
			longerList = longerList.next;
			otherList = otherList.next;
		}

		return otherList;
	}

	private static int length(Node n) {
		int length = 0;
		while (n != null) {
			n = n.next;
			length++;
		}
		return length;
	}

	public static void main(String[] args) {
		Node<String> s = new Node<>("s");
		Node<String> a = new Node<>("a");
		Node<String> b = new Node<>("b");
		Node<String> c = new Node<>("c");
		Node<String> d = new Node<>("d");
		Node<String> e = new Node<>("e");

		Node<String> x = new Node<>("x");
		Node<String> y = new Node<>("y");

		s.next = a;
		a.next = b;
		b.next = c;
		c.next = d;
		d.next = e;

		x.next = y;
		y.next = d;

		System.out.println(findFirstCommonNode(s, x).value);
	}
}