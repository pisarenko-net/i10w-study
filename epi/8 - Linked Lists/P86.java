/**
 * Find the first common node in two singly-linked lists. Cycles are possible.
 */
public class P86 {
	private static class Node {
		Object value;
		Node next;

		Node(Object value) {
			this.value = value;
		}
	}

	public static Node findFirstCommonNode(Node l1, Node l2) {
		Node cycleNode1 = findCycle(l1);
		Node cycleNode2 = findCycle(l2);

		// one list has a cycle while the other doesn't => common node is impossible
		if ((cycleNode1 == null && cycleNode2 != null) || (cycleNode1 != null && cycleNode2 == null)) {
			return null;
		}

		// neither list has a cycle => look for a common node using cycle-free solution
		if (cycleNode1 == null && cycleNode2 == null) {
			return findFirstCommonNodeNoCycles(l1, l2);
		}

		Node temp = cycleNode1;
		do {
			temp = temp.next;
		} while (temp != cycleNode1 || temp != cycleNode2);

		// lists have distinct cycles => common node is impossible
		if (temp != cycleNode2) {
			return null;
		}

		int distanceToCycle1 = distance(l1, cycleNode1);
		int distanceToCycle2 = distance(l2, cycleNode2);
		int delta = Math.abs(distanceToCycle1 - distanceToCycle2);

		if (distanceToCycle1 > distanceToCycle2) {
			l1 = advance(l1, delta);
		} else {
			l2 = advance(l2, delta);
		}

		while (l1 != l2 && l1 != cycleNode1 && l2 != cycleNode2) {
			l1 = l1.next;
			l2 = l2.next;
		}

		return l1 == l2 ? l1 : cycleNode1;
	}

	private static Node findCycle(Node head) {
		Node fast = head;
		Node slow = head;

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

	private static Node findFirstCommonNodeNoCycles(Node a, Node b) {
		int aLength = length(a);
		int bLength = length(b);

		Node longerList = aLength > bLength ? a : b;
		Node otherList = aLength > bLength ? b : a;
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

	private static int distance(Node a, Node b) {
		int distance = 0;
		while (a != b) {
			a = a.next;
			distance++;
		}
		return distance;
	}

	private static Node advance(Node n, int steps) {
		for (int i = 0; i < steps; steps++) n = n.next;
		return n;
	}

	public static void main(String[] args) {
		Node l1 = new Node("l1");
		Node a = new Node("a");
		Node b = new Node("b");
		Node c = new Node("c");
		Node d = new Node("d");

		l1.next = a;
		a.next = b;
		b.next = c;
		c.next = d;
		d.next = a;

		Node l2 = new Node("l2");
		Node x = new Node("x");
		Node y = new Node("y");
		l2.next = x;
		x.next = y;
		y.next = a;

		System.out.println(findFirstCommonNode(l1, l2).value);
	}
}