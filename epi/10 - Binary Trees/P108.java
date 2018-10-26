/**
 * Find a successor node in a binary tree with parent links.
 */
public class P108 {
	private static class Node {
		Object value;
		Node left, right, parent;

		Node(Object value) {
			this.value = value;
		}
	}

	public static Node successor(Node n) {
		if (n.right != null) return deepestLeft(n.right);
		else return highestParentFromLeft(n.parent);
	}

	private static Node deepestLeft(Node n) {
		if (n.left == null) return n;
		else return deepestLeft(n.left);
	}

	private static Node highestParentFromLeft(Node n) {
		if (n.parent == null) return null;
		else if (n.parent.left == n) return n.parent;
		else return highestParentFromLeft(n.parent);
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

		System.out.println(successor(g).value);
		System.out.println(successor(a).value);
		System.out.println(successor(p).value);
	}	
}