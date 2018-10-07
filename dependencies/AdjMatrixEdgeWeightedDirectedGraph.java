import java.util.ArrayList;
import java.util.List;

public class AdjMatrixEdgeWeightedDirectedGraph implements Graph {
	private int edges;
	private Edge[][] adjacencies;

	public AdjMatrixEdgeWeightedDirectedGraph(int vertices) {
		this.adjacencies = new Edge[vertices][vertices];
	}

	public int V() {
		return adjacencies.length;
	}

	public int E() {
		return edges;
	}

	public void addEdge(Edge e) {
		int v = e.from(), w = e.to();
		if (adjacencies[v][w] == null) {
			edges++;
			adjacencies[v][w] = e;
		}
	}

	public Iterable<Edge> adjacentEdges(int v) {
		List<Edge> result = new ArrayList<>();
		for (int w = 0; w < adjacencies.length; w++) {
			if (adjacencies[v][w] != null) result.add(adjacencies[v][w]);
		}
		return result;
	}

	public Iterable<Integer> adjacentVertices(int v) {
		List<Integer> result = new ArrayList<>();
		for (int w = 0; w < adjacencies.length; w++) {
			if (adjacencies[v][w] != null) result.add(w);
		}
		return result;
	}
}