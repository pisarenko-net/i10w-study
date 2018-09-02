import java.util.LinkedList;
import java.util.Queue;
import lombok.*;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private Node root;

	@RequiredArgsConstructor
	private class Node {
		@NonNull Key key;
		@NonNull Value value;
		Node left;
		Node right;
		int size = 1;
		boolean color = RED;
	}

	private boolean isRed(Node n) {
		return n != null ? n.color : BLACK;
	}

	public int size() {
		return size(root);
	}

	private int size(Node n) {
		return n == null ? 0 : n.size;
	}

	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node n, Key key) {
		if (n == null) return null;
		int cmp = key.compareTo(n.key);
		if (cmp < 0) return get(n.left, key);
		else if (cmp > 0) return get(n.right, key);
		else return n.value;
	}

	public void put(Key key, Value value) {
		root = put(root, key, value);
		root.color = BLACK;
	}

	private Node put(Node n, Key key, Value value) {
		if (n == null) return new Node(key, value);
		int cmp = key.compareTo(n.key);
		if (cmp < 0) n.left = put(n.left, key, value);
		else if (cmp > 0) n.right = put(n.right, key, value);
		else n.value = value;

		if (isRed(n.right) && !isRed(n.left)) n = rotateLeft(n);
		if (isRed(n.left) && isRed(n.left.left)) n = rotateRight(n);
		if (isRed(n.left) && isRed(n.right)) flipColors(n);

		n.size = size(n.left) + size(n.right) + 1;
		return n;
	}

	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.size = h.size;
		h.size = 1 + size(h.left) + size(h.right);
		return x;
	}

	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.size = h.size;
		h.size = 1 + size(h.left) + size(h.right);
		return x;
	}

	private void flipColors(Node h) {
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}

	public int height() {
		return height(root);
	}

	private int height(Node n) {
		return n == null ? 0 : Math.max(height(n.left), height(n.right)) + 1;
	}

	public int rank(Key key) {
		return rank(root, key);
	}

	private int rank(Node n, Key key) {
		if (n == null) return 0;
		int cmp = key.compareTo(n.key);
		if (cmp < 0) return rank(n.left, key);
		else if (cmp > 0) return rank(n.right, key) + size(n.left) + 1;
		else return size(n.left);
	}

	public Key select(int rank) {
		Node n = select(root, rank);
		return n == null ? null : n.key;
	}

	private Node select(Node n, int rank) {
		if (n == null) return null;
		int t = size(n.left);
		if (t > rank) return select(n.left, rank);
		else if (t < rank) return select(n.right, rank - size(n.left) - 1);
		else return n;
	}

	public Key floor(Key key) {
		Node n = floor(root, key);
		return n == null ? null : n.key;
	}

	private Node floor(Node n, Key key) {
		if (n == null) return null;
		int cmp = key.compareTo(n.key);
		if (cmp == 0) return n;
		else if (cmp < 0) return floor(n.left, key);
		Node t = floor(n.right, key);
		return t != null ? t : n;
	}

	public Key min() {
		return root == null ? null : min(root).key;
	}

	private Node min(Node n) {
		return n.left == null ? n : min(n.left);
	}

	public Key max() {
		return root == null ? null : max(root).key;
	}

	private Node max(Node n) {
		return n.right == null ? n : max(n.right);
	}

	public Iterable<Key> keys() {
		return keys(min(), max());
	}

	public Iterable<Key> keys(Key min, Key max) {
		Queue<Key> queue = new LinkedList<>();
		keys(root, queue, min, max);
		return queue;
	}

	private void keys(Node n, Queue<Key> queue, Key min, Key max) {
		if (n == null) return;
		int cmplo = min.compareTo(n.key);
		int cmphi = max.compareTo(n.key);
		if (cmplo < 0) keys(n.left, queue, min, max);
		if (cmplo <= 0 && cmphi >= 0) queue.add(n.key);
		if (cmphi > 0) keys(n.right, queue, min, max);
	}

	public static void main(String[] args) {
		RedBlackBST<Integer, String> bst = new RedBlackBST<>();
		//System.out.println(bst.size());
		//System.out.println(bst.height());

		bst.put(1, "jim");
		bst.put(2, "alice");
		bst.put(3, "xxx");
		bst.put(4, "stranger");
		bst.put(5, "sdf");
		bst.put(6, "DoubleFunction");
		bst.put(7, "DoubleConsumer");
		bst.put(8, "while");
		bst.put(9, "CharSequence");
		bst.put(10, "float");


		System.out.println(bst.size());
		System.out.println(bst.height());

		// for (Integer k : bst.keys(2, 30)) {
		// 	System.out.println(k);
		// }
	}
}