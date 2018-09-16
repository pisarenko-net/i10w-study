import java.util.ArrayList;
import java.util.List;

/**
 * Find k largest elements in a BST.
 */
public class P154 {
	private static class Node {
		Comparable key;
		Node left, right;

		Node(Comparable key) {
			this.key = key;
		}
	}

	public static Iterable<Comparable> getLargest(Node root, int k) {
		List<Comparable> result = new ArrayList<>();
		getLargest(root, result, k);
		return result;
	}

	private static void getLargest(Node n, List<Comparable> list, int k) {
		if (n != null && list.size() < k) {
			getLargest(n.right, list, k);
			if (list.size() < k) {
				list.add(n.key);
				getLargest(n.left, list, k);
			}
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

		for (Comparable key : getLargest(a, 5)) {
			System.out.println(key);
		}
	}
}