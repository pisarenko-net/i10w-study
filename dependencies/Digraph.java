import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Digraph implements Graph {
	private final int vertices;
	private int edges;
	private List<Integer>[] adjacencies;

	public Digraph(int vertices) {
		this.vertices = vertices;
		adjacencies = new ArrayList[vertices];
		for (int i = 0; i < vertices; i++) adjacencies[i] = new ArrayList<>();
	}

	public Digraph(Scanner scanner) {
		this(scanner.nextInt());
		int edges = scanner.nextInt();
		for (int i = 0; i < edges; i++) {
			int v = scanner.nextInt();
			int w = scanner.nextInt();
			addEdge(v, w);
		}
	}

	public int V() {
		return vertices;
	}

	public int E() {
		return edges;
	}

	public void addEdge(int v, int w) {
		adjacencies[v].add(w);
		edges++;
	}

	public Iterable<Integer> adj(int v) {
		return new ArrayList<>(adjacencies[v]);
	}

	public Digraph reverse() {
		Digraph g = new Digraph(vertices);
		for (int v = 0; v < vertices; v++) {
			for (int w : adjacencies[v]) {
				g.addEdge(w, v);
			}
		}
		return g;
	}

	public static void main(String[] args) throws Exception {
		Digraph g = new Digraph(new Scanner(new File("dependencies/tinyG.txt")));
	}
}