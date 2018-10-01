import java.util.HashSet;
import java.util.Set;

/**
 * Find LCA of two nodes in a binary tree with parent pointers, optimizing for
 * nodes that are close to each other.
 */
public class P135 {
	public static class Node<T> {
		T key;
		Node<T> left, right, parent;

		public Node(T key) {
			this.key = key;
		}
	}

	public static <T> Node<T> lca(Node<T> a, Node<T> b) {
		Set<Node<T>> visitedNodes = new HashSet<>();

		while (a.parent != null || b.parent != null) {
			if (a.parent != null) {
				a = a.parent;
				if (visitedNodes.contains(a)) return a;
				visitedNodes.add(a);
			}

			if (b.parent != null) {
				b = b.parent;
				if (visitedNodes.contains(b)) return b;
				visitedNodes.add(b);
			}
		}

		return null;
	}

	public static void main(String[] args) {
		Node<String> a = new Node<>("A");
		Node<String> b = new Node<>("B");
		Node<String> c = new Node<>("C");
		Node<String> d = new Node<>("D");
		Node<String> e = new Node<>("E");
		Node<String> f = new Node<>("F");
		Node<String> g = new Node<>("G");
		Node<String> h = new Node<>("H");
		Node<String> i = new Node<>("I");
		Node<String> j = new Node<>("J");
		Node<String> k = new Node<>("K");
		Node<String> l = new Node<>("L");
		Node<String> m = new Node<>("M");
		Node<String> n = new Node<>("N");
		Node<String> o = new Node<>("O");
		Node<String> p = new Node<>("P");

		a.left = b;
		a.right = i;

		b.left = c;
		b.right = f;
		b.parent = a;

		c.left = d;
		c.right = e;
		c.parent = b;

		f.right = g;
		f.parent = b;

		g.left = h;
		g.parent = f;

		i.left = j;
		i.right = o;
		i.parent = a;

		j.right = k;
		j.parent = i;

		k.left = l;
		k.right = n;
		k.parent = j;

		l.right = m;
		l.parent = k;

		o.right = p;
		o.parent = i;

		d.parent = c;
		e.parent = c;
		h.parent = g;
		m.parent = l;
		n.parent = k;
		p.parent = o;

		System.out.println(lca(k, p).key);
	}
}