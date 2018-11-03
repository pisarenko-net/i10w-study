import java.util.Arrays;

/**
 * Implement flood fill.
 */
public class P192 {
	private static final char W = '☐';
	private static final char B = '☒';

	private static final int[][] MOVES = new int[][]{
		{0, 1},
		{1, 0},
		{-1, 0},
		{0, -1}
	};

	public static void floodFill(char[][] grid, int row, int col) {
		char color = grid[row][col];
		fill(grid, row, col, color);
	}

	private static void fill(char[][] grid, int row, int col, char color) {
		grid[row][col] = color == W ? B : W;
		for (int[] move : MOVES) {
			int nextRow = row + move[0];
			int nextCol = col + move[1];
			boolean isRowValid = (nextRow >= 0) && (nextRow < grid.length);
			boolean isColValid = (nextCol >= 0) && (nextCol < grid[0].length);
			if (isRowValid && isColValid && grid[nextRow][nextCol] == color) {
				fill(grid, nextRow, nextCol, color);
			}
		}
	}

	public static void main(String[] args) {
		char[][] grid = new char[][]{
			{B, W, B, W, W, W, B, B, B, B},
			{W, W, B, W, W, B, W, W, B, B},
			{B, B, B, W, W, B, B, W, B, B},
			{W, B, W, B, B, B, W, W, B, W},
			{W, B, B, W, W, W, W, W, W, W},
			{W, B, B, W, W, B, W, B, B, W},
			{W, W, W, W, B, W, W, W, W, W},
			{B, W, B, W, B, W, W, W, W, W},
			{B, W, B, B, W, W, W, B, B, B},
			{B, W, W, W, W, W, W, B, B, W}
		};

		for (char[] line : grid) System.out.println(Arrays.toString(line));

		floodFill(grid, 9, 2);
		System.out.println();

		for (char[] line : grid) System.out.println(Arrays.toString(line));

		floodFill(grid, 0, 9);
		System.out.println();

		for (char[] line : grid) System.out.println(Arrays.toString(line));
	}
}