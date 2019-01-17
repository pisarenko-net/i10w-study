import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class AutoCompleteSystem {
	private static final int MAX_RESULTS = 3;

	private class Node {
		char ch;
		int times;
		Node left, mid, right;
		Node(char ch) {
			this.ch = ch;
		}
	}

	private class Result {
		String query;
		int times;
		Result(String query, int times) {
			this.query = query;
			this.times = times;
		}
	}

	private Node root;
	private StringBuilder prefix = new StringBuilder();

	public AutoCompleteSystem(String[] sentences, int[] times) {
		for (int i = 0; i < sentences.length; i++) root = put(root, sentences[i], times[i], 0);
	}

	private Node put(Node n, String s, int times, int d) {
		char c = s.charAt(d);
		if (n == null) n = new Node(c);
		if (c < n.ch) n.left = put(n.left, s, times, d);
		else if (c > n.ch) n.right = put(n.right, s, times, d);
		else if (d < s.length() - 1) n.mid = put(n.mid, s, times, d + 1);
		else {
			if (times > 0) n.times = times;
			else n.times++;
		}
		return n;
	}
    
	public List<String> input(char c) {
		if (c == '#') {
			put(root, prefix.toString(), 0, 0);
			prefix = new StringBuilder();
			return Collections.emptyList();
		} else {
			prefix.append(c);
			Node curr = get(root, prefix, 0);
			if (curr == null) return Collections.emptyList();
			PriorityQueue<Result> results = new PriorityQueue<>(MAX_RESULTS, new Comparator<Result>(){
				public int compare(Result one, Result other) {
					int timesCmp = Integer.compare(one.times, other.times);
					return (timesCmp != 0 ? timesCmp : one.query.compareTo(other.query)) * -1;  // reverse order
				}
			});
			if (curr.times > 0) results.add(new Result(prefix.toString(), curr.times));
			collect(curr.mid, prefix, results);
			List<String> topResults = new ArrayList<>();
			for (int i = 0; i < MAX_RESULTS && !results.isEmpty(); i++) topResults.add(results.remove().query);
			return topResults;
		}
	}

	private Node get(Node n, StringBuilder prefix, int d) {
		if (n == null) return null;
		char c = prefix.charAt(d);
		if (c < n.ch) return get(n.left, prefix, d);
		else if (c > n.ch) return get(n.right, prefix, d);
		else if (d < prefix.length() - 1) return get(n.mid, prefix, d + 1);
		else return n;
	}

	private void collect(Node n, StringBuilder prefix, PriorityQueue<Result> results) {
		if (n == null) return;
		if (n.times != 0) results.add(new Result(prefix.toString() + n.ch, n.times));
		collect(n.left, prefix, results);
		collect(n.right, prefix, results);
		collect(n.mid, prefix.append(n.ch), results);
		prefix.deleteCharAt(prefix.length() - 1);
	}

	public static void main(String[] args) {
		String[] sentences = new String[]{"i love you", "island", "ironman", "i love code"};
		int[] times = new int[]{5, 3, 2, 2};
		AutoCompleteSystem sys = new AutoCompleteSystem(sentences, times);

		System.out.println(sys.input('i'));
		System.out.println(sys.input(' '));
		System.out.println(sys.input('l'));
		System.out.println(sys.input('#'));

		System.out.println(sys.input('i'));
		System.out.println(sys.input(' '));
		System.out.println(sys.input('l'));
	}
}