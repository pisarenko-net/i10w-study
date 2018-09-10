import lombok.*;

/**
 * Check if a binary tree is balanced: heights of child nodes don't differ by more than 1.
 */
public class P101 {
	@RequiredArgsConstructor
	private static class Node<T> {
		@NonNull T value;
		Node left, right;
	}

	@AllArgsConstructor
	private static class BalanceLookupResult {
		boolean isBalanced;
		int height;
	}

	public static boolean isBalanced(Node tree) {
		BalanceLookupResult lookupResult = getHeightAndBalance(tree);
		return lookupResult.isBalanced;
	}

	private static BalanceLookupResult getHeightAndBalance(Node n) {
		if (n == null) return new BalanceLookupResult(true, 0);

		BalanceLookupResult leftResult = getHeightAndBalance(n.left);
		if (!leftResult.isBalanced) return leftResult;

		BalanceLookupResult rightResult = getHeightAndBalance(n.right);
		if (!rightResult.isBalanced) return rightResult;

		int heightDelta = Math.abs(leftResult.height - rightResult.height);
		int height = Math.max(leftResult.height, rightResult.height) + 1;

		return new BalanceLookupResult(heightDelta <= 1, height);
	}

	public static void main(String[] args) {
		Node<String> a = new Node<>("A");
		Node<String> g = new Node<>("G");
		Node<String> l = new Node<>("L");
		Node<String> r = new Node<>("R");
		Node<String> k = new Node<>("K");
		Node<String> s = new Node<>("S");

		a.left = g;
		a.right = r;

		g.right = l;

		r.left = k;
		//k.left = s;

		System.out.println(isBalanced(a));
	}
}