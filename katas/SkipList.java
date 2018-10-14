import java.util.ArrayList;
import java.util.List;

public class SkipList<Key extends Comparable<Key>, Value> {
	private static final double PROBABILITY = 0.5;

	private class Node {
		Key key;
		Value value;
		List<Node> nextNodes = new ArrayList<>();
		List<Node> prevNodes = new ArrayList<>();

		Node(Key key, Value value) {
			this.key = key;
			this.value = value;
		}
	}

	private Node head;
	private int maxLevel;
	private int count;

	public SkipList() {
		head = new Node(null, null);
		head.nextNodes.add(null);
		head.prevNodes = null;
	}

	public int size() {
		return count;
	}

	public Value get(Key key) {
		Node n = get(head, key, maxLevel);
		return n != null && key.equals(n.key) ? n.value : null;
	}

	private Node get(Node curr, Key key, int level) {
		do {
			curr = getNext(curr, key, level);
		} while (level-- > 0);
		return curr;
	}

	private Node getNext(Node curr, Key key, int level) {
		Node next = curr.nextNodes.get(level);
		while (next != null) {
			if (less(key, next.key)) break;
			curr = next;
			next = curr.nextNodes.get(level);
		}
		return curr;
	}

	private boolean less(Key a, Key b) {
		return a.compareTo(b) < 0;
	}

	public void put(Key key, Value value) {
		if (value == null) throw new IllegalArgumentException("null not allowed");

		Node n = get(head, key, maxLevel);
		if (n != null && key.equals(n.key)) {
			n.value = value;
			return;
		}

		int level = getLevel();

		Node newNode = new Node(key, value);
		Node curr = head;
		do {
			curr = getNext(curr, key, level);
			Node newNodeNext = curr.nextNodes.get(level);
			curr.nextNodes.set(level, newNode);

			newNode.nextNodes.add(0, newNodeNext);
			newNode.prevNodes.add(0, curr);

			if (newNodeNext != null) newNodeNext.prevNodes.set(level, newNode);
		} while (level-- > 0);

		count++;
	}

	private int getLevel() {
		int level = 0;

		while (Math.random() < PROBABILITY) level++;
		while (level > maxLevel) {
			head.nextNodes.add(null);
			maxLevel++;
		}

		return level;
	}

	public void delete(Key key) {
		Node n = get(head, key, maxLevel);
		if (n == null || !key.equals(n.key)) return;

		for (int i = 0; i < n.prevNodes.size(); i++) {
			Node prev = n.prevNodes.get(i);
			Node newNext = n.nextNodes.get(i);
			prev.nextNodes.set(i, newNext);
			if (newNext != null) newNext.prevNodes.set(i, prev);
		}

		count--;
	}

	public Iterable<Key> keys() {
		List<Key> result = new ArrayList<>();
		for (Node curr = head.nextNodes.get(0); curr != null; curr = curr.nextNodes.get(0)) result.add(curr.key);
		return result;
	}

	public static void main(String[] args) {
		SkipList<Integer, String> sl = new SkipList<>();
		sl.put(9, "bug");
		sl.put(0, "zero");
		sl.put(4, "four");
		sl.put(7, "seven");
		sl.put(1, "one");
		sl.put(2, "two");
		sl.put(9, "nine");
		System.out.println("size: " + sl.size());

		for (Integer key : sl.keys()) {
			System.out.println(key + ": " + sl.get(key));
		}

		sl.delete(9);
		sl.delete(2);
		System.out.println("size after two deletes: " + sl.size());

		for (Integer key : sl.keys()) {
			System.out.println(key + ": " + sl.get(key));
		}
	}
}