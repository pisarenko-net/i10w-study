import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UndirectedGraph implements Graph {
	private int edges;
	private List<Integer>[] adj;

	public UndirectedGraph(int vertices) {
		adj = new ArrayList[vertices];
		for (int i = 0; i < vertices; i++) adj[i] = new ArrayList<>();
	}

	public UndirectedGraph(Scanner scanner) {
		this(scanner.nextInt());
		int edges = scanner.nextInt();
		for (int i = 0; i < edges; i++) {
			int v = scanner.nextInt();
			int w = scanner.nextInt();
			addEdge(v, w);
		}
	}

	public int V() {
		return adj.length;
	}

	public int E() {
		return edges;
	}

	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		edges++;
	}

	public Iterable<Integer> adj(int v) {
		return new ArrayList<>(adj[v]);
	}

	public static void main(String[] args) throws Exception {
		Graph g = new UndirectedGraph(new Scanner(new File("dependencies/tinyG.txt")));
	}
}