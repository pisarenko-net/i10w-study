import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class FloydWarshall {
	private boolean hasNegativeCycle;
	private double[][] distTo;
	private Edge[][] edgeTo;

	public FloydWarshall(AdjMatrixEdgeWeightedDirectedGraph g) {
		distTo = new double[g.V()][g.V()];
		edgeTo = new Edge[g.V()][g.V()];

		for (int v = 0; v < g.V(); v++)
			for (int w = 0; w < g.V(); w++)
				distTo[v][w] = Double.POSITIVE_INFINITY;

		for (int v = 0; v < g.V(); v++) {
			for (Edge e : g.adjacentEdges(v)) {
				distTo[e.from()][e.to()] = e.weight();
				edgeTo[e.from()][e.to()] = e;
			}
			if (distTo[v][v] >= 0) {
				distTo[v][v] = 0.0;
				edgeTo[v][v] = null;
			}
		}

		for (int i = 0; i < g.V(); i++) {
			for (int v = 0; v < g.V(); v++) {
				for (int w = 0; w < g.V(); w++) {
					if (distTo[v][w] > distTo[v][i] + distTo[i][w]) {
						distTo[v][w] = distTo[v][i] + distTo[i][w];
						edgeTo[v][w] = edgeTo[i][w];
					}
				}

				if (distTo[v][v] < 0) {
					hasNegativeCycle = true;
					return;
				}
			}
		}
	}

	public boolean hasNegativeCycle() {
		return hasNegativeCycle;
	}

	public Iterable<Integer> negativeCycle() {
		for (int v = 0; v < distTo.length; v++) {
			if (distTo[v][v] < 0) {
				EdgeWeightedDirectedGraph spt = new EdgeWeightedDirectedGraph(distTo.length);
				for (int w = 0; w < distTo.length; w++) {
					if (edgeTo[v][w] != null) spt.addEdge(edgeTo[v][w]);
				}
				DFS dfs = new DFS(spt);
				return dfs.cycle();
			}
		}
		return null;
	}

	public double dist(int s, int t) {
		return distTo[s][t];
	}

	public boolean hasPath(int s, int t) {
		return distTo[s][t] != Double.POSITIVE_INFINITY;
	}

	public Iterable<Edge> path(int s, int t) {
		Deque<Edge> path = new LinkedList<>();
		for (Edge e = edgeTo[s][t]; e != null; e = edgeTo[s][e.from()]) path.push(e);
		return path;
	}

	public static void main(String[] args) {
		// random graph with V vertices and E edges, parallel edges allowed
		int vertices = Integer.parseInt(args[0]);
		int edges = Integer.parseInt(args[1]);
		int seed = Integer.parseInt(args[2]);
		Random random = new Random(seed);

		AdjMatrixEdgeWeightedDirectedGraph g = new AdjMatrixEdgeWeightedDirectedGraph(vertices);
		for (int i = 0; i < edges; i++) {
			int v = random.nextInt(vertices);
			int w = random.nextInt(vertices);
			double weight = Math.round(100 * (random.nextDouble() - 0.15)) / 100.0;
			if (v == w) g.addEdge(new Edge(v, w, Math.abs(weight)));
			else g.addEdge(new Edge(v, w, weight));
		}

		// run Floyd-Warshall algorithm
		FloydWarshall spt = new FloydWarshall(g);

		// print all-pairs shortest path distances
		System.out.print("  ");
		for (int v = 0; v < g.V(); v++) {
			System.out.printf("%6d ", v);
		}
		System.out.println();
		for (int v = 0; v < g.V(); v++) {
			System.out.printf("%3d: ", v);
			for (int w = 0; w < g.V(); w++) {
				if (spt.hasPath(v, w)) System.out.printf("%6.2f ", spt.dist(v, w));
				else System.out.printf("  Inf ");
			}
			System.out.println();
		}

		// print negative cycle
		if (spt.hasNegativeCycle()) {
			System.out.println("Negative cost cycle:");
			for (int v : spt.negativeCycle()) System.out.println(v);
			System.out.println();
		// print all-pairs shortest paths
		} else {
			for (int v = 0; v < g.V(); v++) {
				for (int w = 0; w < g.V(); w++) {
					if (spt.hasPath(v, w)) {
						System.out.printf("%d to %d (%5.2f)  ", v, w, spt.dist(v, w));
						for (Edge e : spt.path(v, w)) System.out.print(e + "  ");
						System.out.println();
					} else {
						System.out.printf("%d to %d no path\n", v, w);
					}
				}
			}
		}
	}
}