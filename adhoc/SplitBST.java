public class SplitBST {
	private static class Node {
		int key;
		Node left, right;

		Node(int key) {
			this.key = key;
		}
	}

	public static Node[] splitBST(Node n, int val) {
		if (n == null) {
			return new Node[2];
		} else if (n.key <= val) {
			Node[] subtrees = splitBST(n.right, val);
			n.right = subtrees[0];
			subtrees[0] = n;
			return subtrees;
		} else {
			Node[] subtrees = splitBST(n.left, val);
			n.left = subtrees[1];
			subtrees[1] = n;
			return subtrees;
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

		Node[] subtrees = splitBST(a, 14);
		System.out.println(subtrees[0].key);
		System.out.println(subtrees[1].key);
	}
}