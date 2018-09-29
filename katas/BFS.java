import java.io.File;
import java.util.Deque;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class BFS {
	private boolean[] marked;
	private int[] edgeTo;
	private int s;

	public BFS(Graph g, int s) {
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		this.s = s;
		bfs(g, s);
	}

	private void bfs(Graph g, int s) {
		Queue<Integer> queue = new LinkedList<>();
		marked[s] = true;
		queue.add(s);

		while (!queue.isEmpty()) {
			int v = queue.remove();
			for (int w : g.adj(v)) {
				if (!marked[w]) {
					marked[w] = true;
					edgeTo[w] = v;
					queue.add(w);
				}
			}
		}
	}

	public boolean hasPathTo(int v) {
		return marked[v];
	}

	public Iterable<Integer> pathTo(int v) {
		if (!hasPathTo(v)) return null;
		Deque<Integer> path = new LinkedList<>();
		for (int x = v; x != s; x = edgeTo[x]) path.push(x);
		path.push(s);
		return path;
	}

	public static void main(String[] args) throws Exception {
		Graph g = new UndirectedGraph(new Scanner(new File("dependencies/tinyG.txt")));
		int s = Integer.parseInt(args[0]);

		BFS bfs = new BFS(g, s);
		for (int v = 0; v < g.V(); v++) {
			if (bfs.hasPathTo(v)) {
				System.out.println(s + " to " + v + ": ");
				for (int x : bfs.pathTo(v)) {
					if (x == s) System.out.print(x);
					else System.out.print("-" + x);
				}
				System.out.println();
			}
		}
	}
}