import java.util.HashSet;
import java.util.Set;

public class DistinctIslands {
	private static int[][] POSSIBLE_MOVES = new int[][]{
		{0, 1},
		{0, -1},
		{1, 0},
		{-1, 0}
	};

	public static int numDistinctIslands(int[][] grid) {
		boolean[][] marked = new boolean[grid.length][grid[0].length];
		Set<Set<Integer>> shapes = new HashSet<>();

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (!marked[i][j]) {
					Set<Integer> points = new HashSet<>();
					dfs(grid, marked, i, j, points);
					if (!points.isEmpty()) {
						points = subtractMins(points);
						shapes.add(points);
					}
				}
			}
		}

		return shapes.size();
	}

	private static void dfs(int[][] grid, boolean[][] marked, int i, int j, Set<Integer> points) {
		marked[i][j] = true;
		if (grid[i][j] != 1) return;
		else points.add(i * 100 + j);
		for (int[] move : POSSIBLE_MOVES) {
			int nextI = move[0] + i;
			int nextJ = move[1] + j;
			if ((nextI >= 0 && nextI < grid.length) && (nextJ >= 0 && nextJ < grid[0].length) && !marked[nextI][nextJ]) {
				dfs(grid, marked, nextI, nextJ, points);
			}
		}
	}

	private static Set<Integer> subtractMins(Set<Integer> points) {
		int minI = Integer.MAX_VALUE, minJ = Integer.MAX_VALUE;
		for (int p : points) {
			minI = Math.min(minI, p / 100);
			minJ = Math.min(minJ, p % 100);
		}
		Set<Integer> minimizedPoints = new HashSet<>();
		for (int p : points) {
			minimizedPoints.add(p - minI * 100 - minJ);
		}
		return minimizedPoints;
	}

	public static void main(String[] args) {
		int[][] grid = new int[][]{
			{1, 1, 0, 0, 0},
			{1, 1, 0, 0, 0},
			{0, 0, 0, 1, 1},
			{0, 0, 0, 1, 1}
		};

		System.out.println(numDistinctIslands(grid));
	}
}