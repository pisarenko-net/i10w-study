/**
 * Compute Lowest Common Ancestor in a BST. Assume distinct values.
 */
public class P155 {
	private static class Node {
		Comparable key;
		Node left, right;

		Node(Comparable key) {
			this.key = key;
		}
	}

	// assume "a" is smaller than "b" for simplicity
	public static Node lca(Node n, Comparable a, Comparable b) {
		if (a.compareTo(n.key) < 0 && b.compareTo(n.key) > 0) {
			return n;
		} else if (a.compareTo(n.key) < 0 && b.compareTo(n.key) < 0) {
			return lca(n.left, a, b);
		} else {
			return lca(n.right, a, b);
		}
	}

	public static void main(String[] args) {
		Node a = new Node(19);
		Node b = new Node(7);
		Node c = new Node(3);
		Node d = new Node(2);
		Node e = new Node(5);
		Node f = new Node(11);
		Node g = new Node(17);
		Node h = new Node(13);
		Node i = new Node(43);
		Node j = new Node(23);
		Node k = new Node(37);
		Node l = new Node(29);
		Node m = new Node(31);
		Node n = new Node(41);
		Node o = new Node(47);
		Node p = new Node(53);

		a.left = b;
		a.right = i;

		b.left = c;
		b.right = f;

		c.left = d;
		c.right = e;

		f.right = g;

		g.left = h;

		i.left = j;
		i.right = o;

		j.right = k;

		k.left = l;
		k.right = n;

		l.right = m;

		o.right = p;

		System.out.println(lca(a, 3, 13).key);
	}
}