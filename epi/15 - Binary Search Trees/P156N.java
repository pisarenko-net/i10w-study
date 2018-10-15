import java.util.Arrays;
import java.util.List;

/**
 * Reconstruct a BST (unique values only) from a preorder traversal in guaranteed O(n) time.
 */
public class P156N {
	private static int rootIdx;

	private static class Node {
		int value;
		Node left, right;

		Node(int value) {
			this.value = value;
		}
	}

	public static Node reconstructTree(List<Integer> traversal) {
		rootIdx = 0;
		return reconstructTree(traversal, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private static Node reconstructTree(List<Integer> traversal, int lowerBound, int upperBound) {
		if (rootIdx == traversal.size()) return null;

		int root = traversal.get(rootIdx);
		if (root < lowerBound || root > upperBound) return null;
		rootIdx++;

		Node n = new Node(root);
		n.left = reconstructTree(traversal, lowerBound, root);
		n.right = reconstructTree(traversal, root, upperBound);
		return n;
	}

	public static void main(String[] args) {
		Node root = reconstructTree(Arrays.asList(108, -10, -14, 285, 243, 401, 600));
		System.out.println(root.value);

		System.out.println(root.left.value);
		System.out.println(root.left.left.value);

		System.out.println(root.right.value);
		System.out.println(root.right.left.value);
		System.out.println(root.right.right.value);
		System.out.println(root.right.right.right.value);
	}
}