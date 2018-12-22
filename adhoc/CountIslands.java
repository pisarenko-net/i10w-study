public class CountIslands {
	private static final boolean T = true;
	private static final boolean F = false;

	private static class DFS {
		boolean[][] marked;
		int count;

		int[][] potentialMoves = new int[][]{
			{0, 1},
			{1, 0},
			{-1, 0},
			{0, -1}
		};

		private DFS(boolean[][] grid) {
			marked = new boolean[grid.length][grid.length];
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid.length; j++) {
					if (!marked[i][j] && grid[i][j]) {
						visit(grid, i, j);
						count++;
					}
				}
			}
		}

		private void visit(boolean[][] grid, int i, int j) {
			marked[i][j] = true;
			
			for (int[] offsets : potentialMoves) {
				int n = offsets[0] + i, m = offsets[1] + j;
				if (isValidMove(grid, n, m) && !marked[n][m] && grid[n][m]) {
					visit(grid, n, m);
				}
			}
		}

		private boolean isValidMove(boolean[][] grid, int i, int j) {
			return (i >= 0 && i < grid.length) && (j >= 0 && j < grid.length) && grid[i][j];
		}
	}

	public static int countIslands(boolean[][] grid) {
		DFS dfs = new DFS(grid);
		return dfs.count;
	}

	public static void main(String[] args) {
		boolean[][] input = new boolean[][]{
			{T, T, T, T, F},
			{T, T, F, T, F},
			{T, T, F, F, F},
			{F, F, F, F, F}
		};
		System.out.println(countIslands(input));

		input = new boolean[][]{
			{T, T, F, F, F},
			{T, T, F, F, F},
			{F, F, T, F, F},
			{F, F, F, T, T}
		};
		System.out.println(countIslands(input));
	}
}