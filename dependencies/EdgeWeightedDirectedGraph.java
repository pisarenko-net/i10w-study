import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EdgeWeightedDirectedGraph implements Graph {
	private List<Edge>[] adjacencies;
	private int edges;

	public EdgeWeightedDirectedGraph(int vertices) {
		adjacencies = (List<Edge>[]) new List[vertices];
		for (int v = 0; v < vertices; v++) adjacencies[v] = new ArrayList<>();
	}

	public EdgeWeightedDirectedGraph(Scanner scanner) {
		this(scanner.nextInt());
		int edges = scanner.nextInt();
		for (int i = 0; i < edges; i++) {
			addEdge(new Edge(scanner.nextInt(), scanner.nextInt(), scanner.nextDouble()));
		}
	}

	public int V() {
		return adjacencies.length;
	}

	public int E() {
		return edges;
	}

	public void addEdge(Edge edge) {
		adjacencies[edge.from()].add(edge);
		edges++;
	}

	public Iterable<Edge> adjacentEdges(int v) {
		return new ArrayList<>(adjacencies[v]);
	}

	public Iterable<Integer> adjacentVertices(int v) {
		return adjacencies[v].stream().map(edge -> edge.to()).collect(Collectors.toList());
	}

	public Iterable<Edge> edges() {
		List<Edge> result = new ArrayList<>();
		for (List<Edge> adj : adjacencies)
			for (Edge e : adj)
				result.add(e);
		return result;
	}

	public static void main(String[] args) throws Exception {
		new EdgeWeightedDirectedGraph(new Scanner(new File("dependencies/tinyEWG.txt")));
	}
}