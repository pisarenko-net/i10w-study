import java.util.Deque;
import java.util.LinkedList;

/**
 * Enumerate a posting list in jump order without using recursion.
 */
public class P96 {
	private static class Node {
		Object value;
		Node next;
		Node jump;
		int order = -1;

		Node(Object value) {
			this.value = value;
		}
	}

	public static void enumerate(Node n) {
		Deque<Node> stack = new LinkedList<>();
		stack.push(n);
		int order = 0;

		while (!stack.isEmpty()) {
			Node curr = stack.pop();
			if (curr != null && curr.order == -1) {
				curr.order = order++;
				stack.push(curr.next);
				stack.push(curr.jump);
			}
		}
	}

	public static void main(String[] args) {
		Node a = new Node("A");
		Node b = new Node("B");
		Node c = new Node("C");
		Node d = new Node("D");

		a.next = b;
		b.next = c;
		c.next = d;

		a.jump = c;
		c.jump = b;
		b.jump = d;
		d.jump = d;

		enumerate(a);

		Node curr = a;
		while (curr != null) {
			System.out.println(curr.value + " " + curr.order);
			curr = curr.next;
		}
	}
}