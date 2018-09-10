/**
 * Check if a tree is BST.
 */
public class P151 {
	public static class Node {
		Comparable key;
		Node left, right;

		Node(Comparable key) {
			this.key = key;
		}
	}

	private static class PrevVisited {
		Comparable key;
	}

	public static boolean isBST(Node tree) {
		return isBST(tree, new PrevVisited());
	}

	private static boolean isBST(Node n, PrevVisited prev) {
		if (n == null) return true;

		boolean leftResult = isBST(n.left, prev);
		if (!leftResult || (prev.key != null && n.key.compareTo(prev.key) < 0)) {
			return false;
		}
		
		prev.key = n.key;
		return isBST(n.right, prev);
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

		System.out.println(isBST(a));
	}
}