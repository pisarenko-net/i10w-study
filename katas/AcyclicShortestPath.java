import java.io.File;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class AcyclicShortestPath {
	private Edge[] edgeTo;
	private double[] distTo;

	public AcyclicShortestPath(EdgeWeightedDirectedGraph g, int s) {
		edgeTo = new Edge[g.V()];
		distTo = new double[g.V()];
		for (int i = 0; i < g.V(); i++) distTo[i] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;

		DFS dfs = new DFS(g, s);
		for (int v : dfs.topological()) {
			relax(g, v);
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

	public double distTo(int v) {
		return distTo[v];
	}

	public boolean hasPathTo(int v) {
		return distTo[v] != Double.POSITIVE_INFINITY;
	}

	public Iterable<Edge> pathTo(int v) {
		if (!hasPathTo(v)) return null;
		Deque<Edge> path = new LinkedList<>();
		for (Edge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) path.add(e);
		return path;
	}

	public static void main(String[] args) throws Exception {
		EdgeWeightedDirectedGraph g = new EdgeWeightedDirectedGraph(new Scanner(new File("dependencies/tinyEWDAG.txt")));
		int s = Integer.parseInt(args[0]);

		AcyclicShortestPath d = new AcyclicShortestPath(g, s);
		for (int t = 0; t < g.V(); t++) {
			System.out.print(s + " to " + t);
			System.out.printf(" (%4.2f): ", d.distTo(t));
			if (d.hasPathTo(t)) {
				for (Edge e : d.pathTo(t)) System.out.print(e + "  ");
			}
			System.out.println();
		}
	}
}