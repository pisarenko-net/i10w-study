import lombok.*;

public class BTree<Key extends Comparable<Key>, Value> {
	private static final int M = 4;

	private static class Node {
		int childCount;
		Entry[] children = new Entry[M];

		Node(int childCount) {
			this.childCount = childCount;
		}
	}

	@AllArgsConstructor
	private static class Entry {
		Comparable key;
		Object value;
		Node next;
	}

	private Node root;
	private int height;
	private int count;

	public BTree() {
		root = new Node(0);
	}

	public int size() {
		return count;
	}

	public int height() {
		return height;
	}

	public Value get(Key key) {
		return get(root, key, height);
	}

	private Value get(Node n, Key key, int currentHeight) {
		if (isExternalNode(currentHeight)) {
			for (int j = 0; j < n.childCount; j++) {
				if (key.equals(n.children[j].key)) return (Value) n.children[j].value;
			}
		} else {
			for (int j = 0; j < n.childCount; j++) {
				if (j+1 == n.childCount || less(key, n.children[j+1].key)) {
					return get(n.children[j].next, key, currentHeight-1);
				}
			}
		}

		return null;
	}

	private boolean isExternalNode(int height) {
		return height == 0;
	}

	private boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}

	public void put(Key key, Value value) {
		Node u = put(root, key, value, height);
		if (u == null) return;

		Node t = new Node(2);
		t.children[0] = new Entry(root.children[0].key, null, root);
		t.children[1] = new Entry(u.children[0].key, null, u);
		root = t;
		height++;
	}

	private Node put(Node n, Key key, Value val, int currentHeight) {
		int j = 0;
		Entry t = new Entry(key, null, null);

		if (isExternalNode(currentHeight)) {
			t.value = val;
			while (j < n.childCount) {
				if (key.equals(n.children[j].key)) {
					n.children[j].value = val;
					return null;
				} else if (less(key, n.children[j].key)) break;
				j++;
			}
			this.count++;
		} else {
			while (j < n.childCount) {
				if (j+1 == n.childCount || less(key, n.children[j+1].key)) {
					Node u = put(n.children[j].next, key, val, currentHeight-1);
					if (u == null) return null;
					t.key = u.children[0].key;
					t.next = u;
					j++;
					break;
				}
				j++;
			}
		}

		for (int i = n.childCount; i > j; i--) n.children[i] = n.children[i-1];
		n.children[j] = t;
		n.childCount++;
		if (n.childCount < M) return null;
		else return split(n);
	}

	private Node split(Node n) {
		Node t = new Node(M/2);
		n.childCount = M/2;
		for (int j = 0; j < M/2; j++)
			t.children[j] = n.children[j+M/2];
		return t;
	}

	public static void main(String[] args) {
        BTree<String, String> st = new BTree<String, String>();

        st.put("www.google.com", "216.239.41.99");
        st.put("www.nytimes.com", "199.239.136.200");
        st.put("www.yahoo.com", "216.109.118.65");
        st.put("alpha", "1");
        st.put("beta", "2");
        st.put("charlie", "3");
        st.put("delta", "4");
        st.put("echo", "5");
        st.put("foxtrot", "6");
        st.put("gamma", "7");
        st.put("hotel", "8");
        st.put("india", "9");
        st.put("julie", "10");
        st.put("www.google.com", "0xFFFF");

        System.out.println("www.google.com:\t" + st.get("www.google.com"));
        System.out.println("www.nytimes.com:\t" + st.get("www.nytimes.com"));
        System.out.println("www.yahoo.com:\t" + st.get("www.yahoo.com"));

        System.out.println("size:\t" + st.size());
        System.out.println("height:\t" + st.height());
	}
}