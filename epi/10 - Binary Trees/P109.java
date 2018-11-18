import java.util.ArrayList;
import java.util.List;

/**
 * Traverse binary tree in order without using recursion and with O(1) memory.
 */
public class P109 {
	private static class Node {
		int value;
		Node left, right, parent;

		Node(int value) {
			this.value = value;
		}

		public String toString() {
			return "" + value;
		}
	}

	public static Iterable<Node> inorder(Node root) {
		List<Node> traversal = new ArrayList<>();
		Node curr = root, prev = null;

		do {
			if (curr.left != null && prev == curr.parent) {
				prev = curr;
				curr = curr.left;
			} else {
				if (prev != curr.right) {
					traversal.add(curr);
				}

				if (curr.right != null && prev != curr.right) {
					prev = curr;
					curr = curr.right;
				} else {
					prev = curr;
					curr = curr.parent;
				}
			}
		} while (curr != null);

		return traversal;
	}

	public static void main(String[] args) {
		Node a = new Node(314);
		Node b = new Node(6);
		Node c = new Node(271);
		Node d = new Node(28);
		Node e = new Node(0);
		Node f = new Node(561);
		Node g = new Node(3);
		Node h = new Node(17);
		Node i = new Node(6);
		Node j = new Node(2);
		Node k = new Node(1);
		Node l = new Node(401);
		Node m = new Node(641);
		Node n = new Node(257);
		Node o = new Node(271);
		Node p = new Node(28);

		a.left = b;
		b.parent = a;
		a.right = i;
		i.parent = a;

		b.left = c;
		c.parent = b;
		b.right = f;
		f.parent = b;

		c.left = d;
		d.parent = c;
		c.right = e;
		e.parent = c;

		f.right = g;
		g.parent = f;

		g.left = h;
		h.parent = g;

		i.left = j;
		j.parent = i;
		i.right = o;
		o.parent = i;

		j.right = k;
		k.parent = j;

		k.left = l;
		l.parent = k;
		k.right = n;
		n.parent = k;

		l.right = m;
		m.parent = l;

		o.right = p;
		p.parent = o;

		for (Node node : inorder(a)) {
			System.out.print(node.value + " ");
		}

		System.out.println();
	}
}