import java.io.File;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class BellmanFord {
	private double[] distTo;
	private Edge[] edgeTo;
	private Iterable<Integer> cycle;

	public BellmanFord(EdgeWeightedDirectedGraph g, int s) {
		edgeTo = new Edge[g.V()];

		distTo = new double[g.V()];
		for (int i = 0; i < g.V(); i++) distTo[i] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;

		for (int i = 0; i < g.V(); i++) {
			for (int v = 0; v < g.V(); v++) {
				relax(g, v);
			}

			findNegativeCycle();
			if (hasNegativeCycle()) return;
		}
	}

	private void relax(EdgeWeightedDirectedGraph g, int v) {
		for (Edge e : g.adjacentEdges(v)) {
			int w = e.to();
			if (distTo[w] > distTo[v] + e.weight()) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
			}
		}
	}

	public boolean hasNegativeCycle() {
		return cycle != null;
	}

	public void findNegativeCycle() {
		EdgeWeightedDirectedGraph spt = new EdgeWeightedDirectedGraph(edgeTo.length);
		for (Edge e : edgeTo) {
			if (e != null) spt.addEdge(e);
		}

		DFS dfs = new DFS(spt);
		cycle = dfs.cycle();
	}

	public Iterable<Integer> negativeCycle() {
		return cycle;
	}

	public double distTo(int v) {
		return distTo[v];
	}

	public boolean hasPathTo(int v) {
		return distTo[v] != Double.POSITIVE_INFINITY;
	}

	public Iterable<Edge> pathTo(int v) {
		if (!hasPathTo(v)) return null;
		Deque<Edge> path = new LinkedList<>();
		for (Edge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) path.push(e);
		return path;
	}

	public static void main(String[] args) throws Exception {
		EdgeWeightedDirectedGraph g = new EdgeWeightedDirectedGraph(new Scanner(new File("dependencies/tinyEWD.txt")));
		int s = Integer.parseInt(args[0]);
		BellmanFord d = new BellmanFord(g, s);

		if (d.hasNegativeCycle()) {
			System.out.println("Negative cycle detected:");
			for (int v : d.negativeCycle()) System.out.println(v);
		} else {
			for (int v = 0; v < g.V(); v++) {
				if (d.hasPathTo(v)) {
					System.out.printf("%d to %d (%4.2f): ", s, v, d.distTo(v));
					for (Edge e : d.pathTo(v)) System.out.print(e + "  ");
					System.out.println();
				} else {
					System.out.printf("%d to %d     no path\n", s, v);
				}
			}
		}
	}
}