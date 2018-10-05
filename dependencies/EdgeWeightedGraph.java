import java.util.ArrayList;
import java.io.File;
import java.util.List;
import java.util.Scanner;

public class EdgeWeightedGraph {
	private final int vertices;
	private int edges;
	private List<Edge>[] adjacencies;

	public EdgeWeightedGraph(int vertices) {
		this.vertices = vertices;
		adjacencies = (List<Edge>[]) new List[vertices];
		for (int i = 0; i < vertices; i++) adjacencies[i] = new ArrayList<Edge>();
	}

	public EdgeWeightedGraph(Scanner scanner) {
		this(scanner.nextInt());
		int edges = scanner.nextInt();
		for (int i = 0; i < edges; i++) {
			addEdge(new Edge(scanner.nextInt(), scanner.nextInt(), scanner.nextDouble()));
		}
	}

	public void addEdge(Edge e) {
		int v = e.either(), w = e.other(v);
		adjacencies[v].add(e);
		adjacencies[w].add(e);
		edges++;
	}

	public int V() {
		return vertices;
	}

	public int E() {
		return edges;
	}

	public Iterable<Edge> adj(int v) {
		return new ArrayList<>(adjacencies[v]);
	}

	public Iterable<Edge> edges() {
		List<Edge> allEdges = new ArrayList<>();
		for (int v = 0; v < vertices; v++) {
			for (Edge e : adjacencies[v]) {
				if (e.other(v) > v) allEdges.add(e);
			}
		}
		return allEdges;
	}

	public static void main(String[] args) throws Exception {
		EdgeWeightedGraph g = new EdgeWeightedGraph(new Scanner(new File("dependencies/tinyEWG.txt")));
	}
}