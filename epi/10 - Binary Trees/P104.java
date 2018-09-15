/**
 * Find LCA (lowest common ancestor) of two nodes when a parent link is available.
 */
public class P104 {
	private static class Node {
		Comparable key;
		Node left, right, parent;

		Node(Comparable key) {
			this.key = key;
		}
	}

	public static Node lca(Node a, Node b) {
		int aHeight = height(a);
		int bHeight = height(b);

		if (aHeight > bHeight) {
			for (int i = 0; i < Math.abs(aHeight - bHeight); i++) a = a.parent;
		} else if (bHeight > aHeight) {
			for (int i = 0; i < Math.abs(aHeight - bHeight); i++) b = b.parent;
		}

		while (a != b) {
			a = a.parent;
			b = b.parent;
		}

		return a;
	}

	private static int height(Node node) {
		int height = 1;
		while (node.parent != null) {
			node = node.parent;
			height++;
		}
		return height;
	}

	public static void main(String[] args) {
		Node a = new Node("A");
		Node b = new Node("B");
		Node c = new Node("C");
		Node d = new Node("D");
		Node e = new Node("E");
		Node f = new Node("F");
		Node g = new Node("G");
		Node h = new Node("H");
		Node i = new Node("I");
		Node j = new Node("J");
		Node k = new Node("K");
		Node l = new Node("L");
		Node m = new Node("M");
		Node n = new Node("N");
		Node o = new Node("O");
		Node p = new Node("P");

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

		System.out.println(lca(p, m).key);
	}
}