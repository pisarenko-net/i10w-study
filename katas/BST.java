import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

public class BST<Key extends Comparable<Key>, Value> {
	private Node root;

	public BST() {}

	private BST(Node root) {
		this.root = root;
	}

	private class Node {
		Key key;
		Value value;
		Node left, right;
		int size = 1;
		Node(Key key, Value value) {
			this.key = key;
			this.value = value;
		}
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
	}

	private Node put(Node n, Key key, Value value) {
		if (n == null) return new Node(key, value);
		int cmp = key.compareTo(n.key);
		if (cmp < 0) n.left = put(n.left, key, value);
		else if (cmp > 0) n.right = put(n.right, key, value);
		else n.value = value;
		n.size = size(n.left) + size(n.right) + 1;
		return n;
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
		return select(root, rank);
	}

	private Key select(Node n, int rank) {
		if (n == null) return null;
		int t = size(n.left);
		if (t > rank) return select(n.left, rank);
		else if (t < rank) return select(n.right, rank - t - 1);
		else return n.key;
	}

	public Key floor(Key key) {
		Node n = floor(root, key);
		return n == null ? null : n.key;
	}

	private Node floor(Node n, Key key) {
		if (n == null) return null;
		int cmp = key.compareTo(n.key);
		if (cmp == 0) return n;
		if (cmp < 0) return floor(n.left, key);
		Node t = floor(n.right, key);
		return t != null ? t : n;
	}

	// assumes unique keys
	public List<BST<Key, Value>> split(Key key) {
		Node[] subtrees = split(root, key);
		return Arrays.asList(new BST<Key, Value>(subtrees[0]), new BST<Key, Value>(subtrees[1]));
	}

	private Node[] split(Node n, Key key) {
		if (n == null) return new BST.Node[2];
		int cmp = n.key.compareTo(key);
		if (cmp <= 0) {
			Node[] subtrees = split(n.right, key);
			n.right = subtrees[0];
			subtrees[0] = n;
			return subtrees;
		} else {
			Node[] subtrees = split(n.left, key);
			n.left = subtrees[1];
			subtrees[1] = n;
			return subtrees;
		}
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

	public Iterable<Key> keys(Key lo, Key hi) {
		Queue<Key> queue = new LinkedList<>();
		keys(root, queue, lo, hi);
		return queue;
	}

	private void keys(Node n, Queue<Key> queue, Key lo, Key hi) {
		if (n == null) return;
		int cmplo = lo.compareTo(n.key);
		int cmphi = hi.compareTo(n.key);
		if (cmplo < 0) keys(n.left, queue, lo, hi);
		if (cmplo <= 0 && cmphi >= 0) queue.add(n.key);
		if (cmphi > 0) keys(n.right, queue, lo, hi);
	}

	public static void main(String[] args) {
		BST<Integer, String> bst = new BST<>();
		//System.out.println(bst.size());
		//System.out.println(bst.height());

		bst.put(4, "stranger");
		bst.put(3, "xxx");
		bst.put(18, "yyy");
		bst.put(2, "alice");
		bst.put(1, "jim");

		System.out.println(bst.get(18));
		System.out.println(bst.get(2));

		System.out.println(bst.size());
		System.out.println(bst.height());
		System.out.println(bst.rank(1));
		System.out.println(bst.select(4));
		System.out.println(bst.floor(89));
		System.out.println(bst.min());
		System.out.println(bst.max());

		List<BST<Integer, String>> subtrees = bst.split(3);
		for (BST<Integer, String> subtree : subtrees) {
			System.out.println("Subtree keys:");
			for (Integer key : subtree.keys()) System.out.println(key);
		}

		// for (Integer k : bst.keys(2, 30)) {
		// 	System.out.println(k);
		// }
	}
}