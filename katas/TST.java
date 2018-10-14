import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TST<Value> {
	private class Node {
		char c;
		Value val;
		Node left, mid, right;

		Node(char c) {
			this.c = c;
		}
	}

	private Node root;

	public Value get(String key) {
		Node n = get(root, key, 0);
		return n != null ? n.val : null;
	}

	private Node get(Node n, String key, int d) {
		if (n == null) return null;
		char c = key.charAt(d);
		if (c < n.c) return get(n.left, key, d);
		else if (c > n.c) return get(n.right, key, d);
		else if (d < key.length()-1) return get(n.mid, key, d+1);
		else return n;
	}

	public void put(String key, Value val) {
		root = put(root, key, val, 0);
	}

	private Node put(Node n, String key, Value val, int d) {
		char c = key.charAt(d);
		if (n == null) n = new Node(c);
		if (c < n.c) n.left = put(n.left, key, val, d);
		else if (c > n.c) n.right = put(n.right, key, val, d);
		else if (d < key.length()-1) n.mid = put(n.mid, key, val, d+1);
		else n.val = val;
		return n;
	}

	public Iterable<String> keys() {
		List<String> q = new ArrayList<>();
		collect(root, new StringBuilder(), q);
		return q;
	}

	private void collect(Node n, StringBuilder pre, List<String> q) {
		if (n == null) return;
		collect(n.left, pre, q);
		collect(n.right, pre, q);
		if (n.val != null) q.add(pre.toString() + n.c);
		collect(n.mid, pre.append(n.c), q);
		pre.deleteCharAt(pre.length() - 1);
	}

	public Iterable<String> keysWithPrefix(String pre) {
		Node n = get(root, pre, 0);
		if (n == null) return Collections.emptyList();
		List<String> q = new ArrayList<>();
		if (n.val != null) q.add(pre);
		collect(n.mid, new StringBuilder(pre), q);
		return q;
	}

	public String longestPrefixOf(String s) {
		int length = 0, i = 0;
		Node n = root;
		while (n != null && i < s.length()) {
			char c = s.charAt(i);
			if (c < n.c) n = n.left;
			else if (c > n.c) n = n.right;
			else {
				i++;
				if (n.val != null) length = i;
				n = n.mid;
			}
		}
		return s.substring(0, length);
	}

	public static void main(String[] args) {
		TST<Integer> tst = new TST<>();
		tst.put("hello", 13);
		tst.put("he", 0);
		tst.put("jelly", 7);
		tst.put("stop", 3);
		tst.put("gender", 39);
		tst.put("helloz", 98);
		tst.put("hello my friend", 15);

		System.out.println(tst.get("helloz"));
		System.out.println(tst.get("hhelloz"));
		System.out.println(tst.get("he"));
		System.out.println(tst.get("hello"));
		System.out.println(tst.get("hell"));

		System.out.println("\nKeys starting with h:");
		for (String s : tst.keysWithPrefix("h")) System.out.println(s);
		System.out.println("\nALL keys:");
		for (String s : tst.keys()) System.out.println(s);
		System.out.println("\nLongest prefix of jellybeans:");
		System.out.println(tst.longestPrefixOf("jellybeans"));
	}
}