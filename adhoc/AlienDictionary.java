import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class AlienDictionary {
	private Map<Character, Set<Character>> adjacencies = new HashMap<>();
	private DFS dfs;
	private String ordering;

	private class DFS {
		private Map<Character, Boolean> marked = new HashMap<>();
		private Deque<Character> reversePostorder = new LinkedList<>();
		private boolean hasCycle;
		private Map<Character, Boolean> onStack = new HashMap<>();

		private DFS() {
			Iterable<Character> vertices = adjacencies.keySet();
			for (char v : vertices) {
				if (marked.get(v) == null) visit(v);
			}
		}

		private void visit(char v) {
			marked.put(v, true);
			Set<Character> adjacentVertices = adjacencies.get(v);

			onStack.put(v, true);
			if (adjacentVertices != null) {
				for (char w : adjacentVertices) {
					if (marked.get(w) == null) visit(w);
					else if (onStack.containsKey(w)) hasCycle = true;
				}
			}
			onStack.remove(v);

			reversePostorder.push(v);
		}

		public Iterable<Character> topological() {
			return !hasCycle ? new ArrayList<>(reversePostorder) : null;
		}
	}
	

	AlienDictionary(List<String> words) {
		buildGraph(words);
		dfs = new DFS();
		computeOrdering();
	}

	private void buildGraph(List<String> words) {
		for (int i = 1; i < words.size(); i++) {
			String word1 = words.get(i - 1);
			String word2 = words.get(i);
			int len = word1.length() < word2.length() ? word1.length() : word2.length();

			for (int j = 0; j < len; j++) {
				if (word1.charAt(j) != word2.charAt(j)) {
					addEdge(word1.charAt(j), word2.charAt(j));
				}
			}
		}
	}

	private void addEdge(Character a, Character b) {
		adjacencies.putIfAbsent(a, new HashSet<>());
		adjacencies.get(a).add(b);
	}

	private void computeOrdering() {
		if (dfs.topological() == null) return;
		StringBuilder sb = new StringBuilder();
		for (char c : dfs.topological()) sb.append(c);
		ordering = sb.toString();
	}

	public String ordering() {
		return ordering;
	}

	public static void main(String[] args) {
		List<String> input = Arrays.asList("wrt", "wrf", "er", "ett", "rftt");
		AlienDictionary dict = new AlienDictionary(input);
		System.out.println(dict.ordering());

		input = Arrays.asList("z", "x");
		dict = new AlienDictionary(input);
		System.out.println(dict.ordering());

		input = Arrays.asList("z", "x", "z");
		dict = new AlienDictionary(input);
		System.out.println(dict.ordering());
	}
}