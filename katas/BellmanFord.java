import java.io.File;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BellmanFord {
	private double[] distTo;
	private Edge[] edgeTo;
	private boolean[] onQueue;
	private Queue<Integer> queue;
	private Iterable<Integer> cycle;
	private int cost;

	public BellmanFord(EdgeWeightedDirectedGraph g, int s) {
		distTo = new double[g.V()];
		for (int i = 0; i < g.V(); i++) distTo[i] = Double.POSITIVE_INFINITY;
		edgeTo = new Edge[g.V()];
		onQueue = new boolean[g.V()];
		queue = new LinkedList<>();

		distTo[s] = 0.0;
		onQueue[s] = true;
		queue.add(s);

		while (!queue.isEmpty() && !hasNegativeCycle()) {
			int v = queue.remove();
			onQueue[v] = false;
			relax(g, v);
		}
	}

	private void relax(EdgeWeightedDirectedGraph g, int v) {
		for (Edge e : g.adjacentEdges(v)) {
			int w = e.to();
			if (distTo[w] > distTo[v] + e.weight()) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if (!onQueue[w]) {
					queue.add(w);
					onQueue[w] = true;
				}
			}

			if (cost++ % g.V() == 0) {
				findNegativeCycle();
				if (hasNegativeCycle()) return;
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