import java.io.File;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DFS {
	private boolean[] marked;
	private boolean[] onStack;
	private int[] edgeTo;
	private int s;

	private Deque<Integer> cycle;

	private Queue<Integer> preorder;
	private Queue<Integer> postorder;
	private Deque<Integer> reversePostorder;

	public DFS(Graph g, int s) {
		preorder = new LinkedList<>();
		postorder = new LinkedList<>();
		reversePostorder = new LinkedList<>();

		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		onStack = new boolean[g.V()];
		this.s = s;
		dfs(g, s);
	}

	private void dfs(Graph g, int v) {
		preorder.add(v);

		onStack[v] = true;
		marked[v] = true;
		for (int w : g.adj(v)) {
			if (this.hasCycle()) return;
			else if (!marked[w]) {
				edgeTo[w] = v;
				dfs(g, w);
			} else if (onStack[w]) {
				cycle = new LinkedList<>();
				for (int x = v; x != w; x = edgeTo[x]) cycle.push(x);
				cycle.push(w);
				cycle.push(v);
			}
		}
		onStack[v] = false;

		postorder.add(v);
		reversePostorder.push(v);
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

	public Iterable<Integer> preorder() {
		return new LinkedList<>(preorder);
	}

	public Iterable<Integer> postorder() {
		return new LinkedList<>(postorder);
	}

	public Iterable<Integer> reversePostorder() {
		return new LinkedList<>(reversePostorder);
	}

	public Iterable<Integer> topological() {
		return reversePostorder();
	}

	public boolean hasCycle() {
		return cycle != null;
	}

	public Iterable<Integer> cycle() {
		return new LinkedList<>(cycle);
	}

	public static void main(String[] args) throws Exception {
		Graph g = new Digraph(new Scanner(new File("dependencies/tinyG.txt")));
		int s = Integer.parseInt(args[0]);

		DFS dfs = new DFS(g, s);

		System.out.println("Graph has cycle: " + dfs.hasCycle());
		if (dfs.hasCycle()) {
			for (int x : dfs.cycle()) System.out.print(x + " ");
			System.out.println();
			return;
		}

		for (int v = 0; v < g.V(); v++) {
			if (dfs.hasPathTo(v)) {
				System.out.print(s + " to " + v + ": ");
				for (int x : dfs.pathTo(v)) {
					if (x == s) System.out.print(x);
					else System.out.print("-" + x);
				}
				System.out.println();
			}
		}
	}
}