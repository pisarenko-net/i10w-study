/**
 * Check if a binary tree contains a node, whose root-to-node sum equals to the given argument.
 */
public class P106 {
	private static class Node {
		Integer value;
		Node left, right;

		Node(Integer value) {
			this.value = value;
		}
	}

	public static boolean existsPathWithWeight(Node a, int totalWeight) {
		Node n = findNodeWithWeight(a, 0, totalWeight);
		return n != null ? true : false;
	}

	private static Node findNodeWithWeight(Node n, int currentSum, int totalWeight) {
		if (n == null) return null;
		int weightAtNode = currentSum + n.value;
		if (weightAtNode > totalWeight) return null;
		else if (weightAtNode == totalWeight) return n;
		else {
			Node leftResult = findNodeWithWeight(n.left, weightAtNode, totalWeight);
			return leftResult != null ? leftResult : findNodeWithWeight(n.right, weightAtNode, totalWeight);
		}
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

		System.out.println(existsPathWithWeight(a, 591));
	}
}