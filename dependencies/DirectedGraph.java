import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DirectedGraph implements Graph {
	private int edges;
	private List<Integer>[] adjacencies;

	public DirectedGraph(int vertices) {
		adjacencies = new ArrayList[vertices];
		for (int i = 0; i < vertices; i++) adjacencies[i] = new ArrayList<>();
	}

	public DirectedGraph(Scanner scanner) {
		this(scanner.nextInt());
		int edges = scanner.nextInt();
		for (int i = 0; i < edges; i++) {
			int v = scanner.nextInt();
			int w = scanner.nextInt();
			addEdge(v, w);
		}
	}

	public int V() {
		return adjacencies.length;
	}

	public int E() {
		return edges;
	}

	public void addEdge(int v, int w) {
		adjacencies[v].add(w);
		edges++;
	}

	public Iterable<Integer> adjacentVertices(int v) {
		return new ArrayList<>(adjacencies[v]);
	}

	public DirectedGraph reverse() {
		int vertices = adjacencies.length;
		DirectedGraph g = new DirectedGraph(vertices);
		for (int v = 0; v < vertices; v++) {
			for (int w : adjacencies[v]) {
				g.addEdge(w, v);
			}
		}
		return g;
	}

	public static void main(String[] args) throws Exception {
		DirectedGraph g = new DirectedGraph(new Scanner(new File("dependencies/tinyG.txt")));
	}
}