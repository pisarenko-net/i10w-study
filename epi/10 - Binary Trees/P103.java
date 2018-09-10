import java.util.Deque;
import java.util.LinkedList;

import lombok.*;

/**
 * Find Lowest Common Ancestor (LCA) in a binary tree (not BST!). No parent links are available.
 */
public class P103 {
	private static class Node<T> {
		T value;
		Node left, right;

		public Node(T value) {
			this.value = value;
		}
	}

	@AllArgsConstructor
	private static class LookupResult<T> {
		int nodesFound;
		Node<T> ancestor;
	}

	public static <T> T lca(Node<T> node, T a, T b) {
		return lcaHelper(node, a, b).ancestor.value;
	}

	private static <T> LookupResult<T> lcaHelper(Node<T> node, T a, T b) {
		if (node == null) return new LookupResult<T>(0, null);

		LookupResult<T> leftResult = lcaHelper(node.left, a, b);
		if (leftResult.nodesFound == 2) return leftResult;

		LookupResult<T> rightResult = lcaHelper(node.right, a, b);
		if (rightResult.nodesFound == 2) return rightResult;

		int nodesFound = leftResult.nodesFound + rightResult.nodesFound;
		if (node.value.equals(a) || node.value.equals(b)) nodesFound++;

		return new LookupResult<T>(nodesFound, node);
	}

	public static void main(String[] args) {
		Node<String> a = new Node("A");
		Node<String> b = new Node("B");
		Node<String> c = new Node("C");
		Node<String> d = new Node("D");
		Node<String> e = new Node("E");
		Node<String> f = new Node("F");
		Node<String> g = new Node("G");
		Node<String> h = new Node("H");
		Node<String> i = new Node("I");
		Node<String> j = new Node("J");
		Node<String> k = new Node("K");
		Node<String> l = new Node("L");
		Node<String> m = new Node("M");
		Node<String> n = new Node("N");
		Node<String> o = new Node("O");
		Node<String> p = new Node("P");

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

		System.out.println(lca(a, "N", "M"));
	}
}