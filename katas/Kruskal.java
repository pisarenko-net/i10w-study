import java.util.ArrayList;
import java.io.File;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

import edu.princeton.cs.algs4.UF;

public class Kruskal {
	private List<Edge> mst = new ArrayList<>();

	public Kruskal(EdgeWeightedGraph g) {
		UF uf = new UF(g.V());

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (Edge e : g.edges()) pq.add(e);
			
		while (!pq.isEmpty() && mst.size() < (g.V() - 1)) {
			Edge e = pq.remove();
			int v = e.either(), w = e.other(v);
			if (uf.connected(v, w)) continue;
			uf.union(v, w);
			mst.add(e);
		}
	}

	public Iterable<Edge> edges() {
		return new ArrayList<>(mst);
	}

	public double weight() {
		double sum = 0;
		for (Edge e : edges()) sum += e.weight();
		return sum;
	}

	public static void main(String[] args) throws Exception {
		EdgeWeightedGraph g = new EdgeWeightedGraph(new Scanner(new File("dependencies/tinyEWG.txt")));
		Kruskal mst = new Kruskal(g);
		mst.edges().forEach(System.out::println);
		System.out.printf("%.5f\n", mst.weight());
	}
}