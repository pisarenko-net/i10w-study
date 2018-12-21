import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Return a binary tree's elements as a list of nodes, broken down by depth in the order as items appear in the tree.
 */
public class P99 {
	private static class Node {
		int value;
		Node left, right;
		Node(int value) {
			this.value = value;
		}
	}

	public static List<List<Integer>> getNodesByDepth(Node root) {
		Queue<Node> primary = new LinkedList<>();
		Queue<Node> secondary = new LinkedList<>();

		List<List<Integer>> depths = new ArrayList<>();
		primary.add(root);

		while (!primary.isEmpty()) {
			List<Integer> nodesAtCurrentDepth = new ArrayList<>();

			while (!primary.isEmpty()) {
				Node node = primary.remove();
				nodesAtCurrentDepth.add(node.value);
				if (node.left != null) secondary.add(node.left);
				if (node.right != null) secondary.add(node.right);
			}

			depths.add(nodesAtCurrentDepth);

			Queue<Node> temp = primary;
			primary = secondary;
			secondary = temp;
		}

		return depths;
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

		for (List<Integer> depth : getNodesByDepth(a)) {
			for (Integer val : depth) {
				System.out.print(val + " ");
			}
			System.out.println();
		}

	}
}