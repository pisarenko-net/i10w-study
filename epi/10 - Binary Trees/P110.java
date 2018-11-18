import java.util.Arrays;
import java.util.List;

/**
 * Reconstruct binary tree (not BST) from inorder+preorder traversals.
 */
public class P110 {
	private static class Node {
		char value;
		Node left, right;
		Node(char value) {
			this.value = value;
		}
	}

	public static Node reconstruct(List<Character> inorder, List<Character> preorder) {
		return reconstruct(inorder, preorder, 0, 0, inorder.size()-1);
	}

	private static Node reconstruct(List<Character> inorder, List<Character> preorder, int rootIdx, int lo, int hi) {
		char root = preorder.get(rootIdx);
		Node node = new Node(root);

		int leftChildrenCount = 0, rightChildrenCount = 0;
		for (int i = lo; i <= hi && inorder.get(i) != root; leftChildrenCount++, i++);
		for (int i = lo + leftChildrenCount + 1; i <= hi; rightChildrenCount++, i++);

		if (leftChildrenCount > 0) node.left = reconstruct(inorder, preorder, rootIdx+1, lo, lo + leftChildrenCount - 1);
		if (rightChildrenCount > 0) node.right = reconstruct(inorder, preorder, rootIdx + leftChildrenCount + 1, lo + leftChildrenCount + 1, hi);

		return node;
	}

	public static void main(String[] args) {
		List<Character> inorder = Arrays.asList('F', 'B', 'A', 'E', 'H', 'C', 'D', 'I', 'G');
		List<Character> preorder = Arrays.asList('H', 'B', 'F', 'E', 'A', 'C', 'D', 'G', 'I');

		Node root = reconstruct(inorder, preorder);

		Node h = root;

		Node b = h.left;
		Node c = h.right;

		Node f = b.left;
		Node e = b.right;

		Node a = e.left;

		Node d = c.right;
		Node g = d.right;
		Node i = g.left;

		System.out.println(h.value);
		System.out.println(b.value);
		System.out.println(f.value);
		System.out.println(e.value);
		System.out.println(a.value);
		System.out.println(c.value);
		System.out.println(d.value);
		System.out.println(g.value);
		System.out.println(i.value);
	}
}