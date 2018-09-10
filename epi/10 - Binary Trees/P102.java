import java.util.LinkedList;
import java.util.Queue;

import lombok.*;

/**
 * Check if a given tree is symmetric (half of a tree is a mirrored copy of the other).
 */
public class P102 {
	@RequiredArgsConstructor
	private static class Node<T> {
		@NonNull T value;
		Node left, right;
	}

	@AllArgsConstructor
	private static class ProcessNode<T> {
		Node<T> node;
		boolean isLeft;
	}

	public static <T> boolean isSymmetric(Node<T> tree) {
		Queue<ProcessNode<T>> leftQueue = new LinkedList<>();
		Queue<ProcessNode<T>> rightQueue = new LinkedList<>();

		if (!areNodeValuesEqual(tree.left, tree.right)) return false;

		leftQueue.add(new ProcessNode<>(tree.left, true));
		rightQueue.add(new ProcessNode<>(tree.right, false));

		while (!leftQueue.isEmpty() && !rightQueue.isEmpty()) {
			ProcessNode<T> topLeft = leftQueue.remove();
			ProcessNode<T> topRight = rightQueue.remove();

			if (!areNodeValuesEqual(topLeft.node, topRight.node)) return false;
			if (!areNodesSymmetric(topLeft, topRight)) return false;

			if (topLeft.node.left != null) leftQueue.add(new ProcessNode<>(topLeft.node.left, true));
			if (topLeft.node.right != null) leftQueue.add(new ProcessNode<>(topLeft.node.right, false));

			if (topRight.node.right != null) rightQueue.add(new ProcessNode<>(topRight.node.right, false));
			if (topRight.node.left != null) rightQueue.add(new ProcessNode<>(topRight.node.left, true));
		}

		return leftQueue.isEmpty() && rightQueue.isEmpty();
	}

	private static <T> boolean areNodeValuesEqual(Node<T> a, Node<T> b) {
		return (a == null && b == null) || ((a != null && b != null && a.value.equals(b.value)));
	}

	private static <T> boolean areNodesSymmetric(ProcessNode<T> a, ProcessNode<T> b) {
		return (a.node == null && b.node == null) || (a.node != null && b.node != null && a.isLeft != b.isLeft);
	}

	public static void main(String[] args) {
		Node<Integer> a = new Node<>(314);
		Node<Integer> b = new Node<>(6);
		Node<Integer> c = new Node<>(6);
		Node<Integer> d = new Node<>(1);
		Node<Integer> e = new Node<>(2);
		Node<Integer> f = new Node<>(3);
		Node<Integer> g = new Node<>(2);
		Node<Integer> h = new Node<>(1);
		Node<Integer> i = new Node<>(3);

		a.left = b;
		a.right = c;

		b.left = d;
		b.right = e;

		e.right = f;

		c.left = g;
		c.right = h;

		g.left = i;

		System.out.println(isSymmetric(a));
	}
}