import java.io.File;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DFS {
	private boolean[] marked;
	private int[] edgeTo;
	private int s;

	private Queue<Integer> preorder;
	private Queue<Integer> postorder;
	private Deque<Integer> reversePostorder;

	public DFS(Graph g, int s) {
		preorder = new LinkedList<>();
		postorder = new LinkedList<>();
		reversePostorder = new LinkedList<>();

		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		this.s = s;
		dfs(g, s);
	}

	private void dfs(Graph g, int v) {
		preorder.add(v);

		marked[v] = true;
		for (int w : g.adj(v)) {
			if (!marked[w]) {
				edgeTo[w] = v;
				dfs(g, w);
			}
		}

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

	public static void main(String[] args) throws Exception {
		Graph g = new Digraph(new Scanner(new File("dependencies/tinyG.txt")));
		int s = Integer.parseInt(args[0]);

		DFS dfs = new DFS(g, s);
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