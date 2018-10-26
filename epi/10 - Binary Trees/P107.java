/**
 * Find kth node in a binary tree in which nodes indicate number of nodes in the subtree rooted at node.
 */
public class P107 {
	private static class Node {
		int value;
		Node left, right;
		int size;

		Node(int value, int size) {
			this.value = value;
			this.size = size;
		}
	}

	public static Node findKthNode(Node n, int k) {
		int leftSize = n.left == null ? 0 : n.left.size;
		if (leftSize == k - 1) return n;
		else if (leftSize + 1 < k) return findKthNode(n.right, k - 1 - leftSize);
		else return findKthNode(n.left, k);
	}

	public static void main(String[] args) {
		Node a = new Node(314, 16);
		Node b = new Node(6, 7);
		Node c = new Node(271, 3);
		Node d = new Node(28, 1);
		Node e = new Node(0, 1);
		Node f = new Node(561, 3);
		Node g = new Node(3, 2);
		Node h = new Node(17, 1);
		Node i = new Node(6, 8);
		Node j = new Node(2, 5);
		Node k = new Node(1, 4);
		Node l = new Node(401, 2);
		Node m = new Node(641, 1);
		Node n = new Node(257, 1);
		Node o = new Node(271, 2);
		Node p = new Node(28, 1);

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

		System.out.println(findKthNode(a, 6).value);
	}
}