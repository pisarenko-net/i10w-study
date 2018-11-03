import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Generate all possible binary trees given node count.
 */
public class P168 {
	private static class Node {
		Node left, right;

		Node(Node left, Node right) {
			this.left = left;
			this.right = right;
		}
	}

	public static List<Node> createTrees(int nodes) {
		if (nodes == 0) return Collections.singletonList(null);

		List<Node> subtrees = new ArrayList<>();
		for (int leftNodes = 0; leftNodes < nodes; leftNodes++) {
			int rightNodes = nodes - leftNodes - 1;
			List<Node> leftSubtrees = createTrees(leftNodes);
			List<Node> rightSubtrees = createTrees(rightNodes);

			for (Node left : leftSubtrees) {
				for (Node right : rightSubtrees) {
					subtrees.add(new Node(left, right));
				}
			}
		}

		return subtrees;
	}

	public static void main(String[] args) {
		for (Node tree : createTrees(3)) {
			System.out.println(tree);
		}
	}
}