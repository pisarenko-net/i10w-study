import java.util.Arrays;
import java.util.List;

/**
 * Reconstruct a BST (unique values) from a post order.
 */
public class P156R {
	private static class Node {
		int value;
		Node left, right;

		Node(int value) {
			this.value = value;
		}
	}

	public static Node reconstructTree(List<Integer> traversal) {
		return reconstructTree(traversal, 0, traversal.size()-1);
	}

	private static Node reconstructTree(List<Integer> traversal, int lo, int hi) {
		if (hi < lo) return null;
		Node n = new Node(traversal.get(hi));
		int mid = hi - 1;
		while (mid >= lo && traversal.get(mid) > traversal.get(hi)) mid--;
		n.right = reconstructTree(traversal, mid+1, hi-1);
		n.left = reconstructTree(traversal, lo, mid);
		return n;
	}

	public static void main(String[] args) {
		Node root = reconstructTree(Arrays.asList(-14, -10, 243, 600, 401, 285, 108));
		System.out.println(root.value);

		System.out.println(root.left.value);
		System.out.println(root.left.left.value);

		System.out.println(root.right.value);
		System.out.println(root.right.left.value);
		System.out.println(root.right.right.value);
		System.out.println(root.right.right.right.value);
	}
}