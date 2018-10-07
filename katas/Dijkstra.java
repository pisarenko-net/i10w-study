import java.io.File;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

import edu.princeton.cs.algs4.IndexMinPQ;

public class Dijkstra {
	private Edge[] edgeTo;
	private double[] distTo;
	private IndexMinPQ<Double> pq;

	public Dijkstra(EdgeWeightedDirectedGraph g, int s) {
		edgeTo = new Edge[g.V()];
		distTo = new double[g.V()];
		for (int i = 0; i < g.V(); i++) distTo[i] = Double.POSITIVE_INFINITY;
		pq = new IndexMinPQ<>(g.V());

		distTo[s] = 0.0;
		pq.insert(s, 0.0);

		while (!pq.isEmpty()) {
			relax(g, pq.delMin());
		}
	}

	private void relax(EdgeWeightedDirectedGraph g, int v) {
		for (Edge e : g.adjacentEdges(v)) {
			int w = e.to();
			if (distTo[w] > distTo[v] + e.weight()) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if (pq.contains(w)) pq.change(w, distTo[w]);
				else pq.insert(w, distTo[w]);
			}
		}
	}

	public boolean hasPathTo(int v) {
		return distTo[v] != Double.POSITIVE_INFINITY;
	}

	public double distTo(int v) {
		return distTo[v];
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

		Dijkstra d = new Dijkstra(g, s);
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