import java.util.Arrays;
import java.util.List;

/**
 * Reconstruct a BST (unique values only) from a preorder traversal.
 */
public class P156 {
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
		Node n = new Node(traversal.get(lo));
		int mid = lo + 1;
		while (mid <= hi && traversal.get(mid) < traversal.get(lo)) mid++;
		n.left = reconstructTree(traversal, lo+1, mid-1);
		n.right = reconstructTree(traversal, mid, hi);
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