/**
 * Compute sum of binary numbers stored as paths from root to leafs.
 */
public class P105 {
	private static class Node {
		boolean isSet;
		Node left, right;

		Node(boolean isSet) {
			this.isSet = isSet;
		}
	}

	public static long sumBinaryPaths(Node n) {
		return sumBinaryPaths(n, 0, 0);
	}

	public static long sumBinaryPaths(Node n, long sum, int ancestorAccumulator) {
		int currNumber = ((ancestorAccumulator << 1) | (n.isSet ? 1 : 0));

		if (n.left == null && n.right == null) {
			return sum + currNumber;
		} else  {
			if (n.left != null) {
				sum += sumBinaryPaths(n.left, sum, currNumber);
			}

			if (n.right != null) {
				sum += sumBinaryPaths(n.right, sum, currNumber);
			}

			return sum;
		}
	}

	public static void main(String[] args) {
		Node a = new Node(true);
		Node b = new Node(false);
		Node c = new Node(false);
		Node d = new Node(false);
		Node e = new Node(true);
		Node f = new Node(true);
		Node g = new Node(true);
		Node h = new Node(false);
		Node i = new Node(true);
		Node j = new Node(false);
		Node k = new Node(false);
		Node l = new Node(true);
		Node m = new Node(true);
		Node n = new Node(false);
		Node o = new Node(false);
		Node p = new Node(false);

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

		System.out.println(sumBinaryPaths(a));
	}
}