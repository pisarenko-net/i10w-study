/**
 * Find first occurence of a key in a BST.
 */
public class P152 {
	private static class Node<T extends Comparable<T>> {
		T key;
		Node<T> left, right;

		Node(T key) {
			this.key = key;
		}
	}

	public static <T extends Comparable<T>> Node<T> findFirst(Node<T> root, T key) {
		Node<T> foundSoFar = null;
		Node<T> curr = root;

		while (curr != null) {
			if (key.compareTo(curr.key) < 0) {
				curr = curr.left;
			} else if (key.compareTo(curr.key) > 0) {
				curr = curr.right;
			} else {
				foundSoFar = curr;
				curr = curr.left;
			}
		}

		return foundSoFar;
	}

	public static void main(String[] args) {
		Node<Integer> a = new Node<>(108);
		Node<Integer> b = new Node<>(108);
		Node<Integer> c = new Node<>(-10);
		Node<Integer> d = new Node<>(-14);
		Node<Integer> e = new Node<>(2);
		Node<Integer> f = new Node<>(108);
		Node<Integer> g = new Node<>(285);
		Node<Integer> h = new Node<>(243);
		Node<Integer> i = new Node<>(285);
		Node<Integer> j = new Node<>(401);

		a.left = b;
		a.right = g;

		b.left = c;
		b.right = f;

		c.left = d;
		c.right = e;

		g.left = h;
		g.right = i;

		i.right = j;

		Node<Integer> res = findFirst(a, 285);
		System.out.println(res == g);

		res = findFirst(a, 108);
		System.out.println(res == b);

		res = findFirst(a, 143);
		System.out.println(res == null);
	}
}