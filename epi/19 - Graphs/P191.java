import java.util.Arrays;

/**
 * Find/mark a path in a maze.
 */
public class P191 {
	private static final char W = '☐';
	private static final char B = '☒';
	private static final char E = '☻';
	private static final char S = '☺';
	private static final char P = '☑';

	private static final int[][] moves = new int[][]{
		{0, -1},
		{0, 1},
		{1, 0},
		{-1, 0}
	};

	public static boolean findPath(char[][] maze, int x, int y) {
		if (maze[y][x] == E) return true;

		for (int[] move : moves) {
			int nextX = move[0] + x, nextY = move[1] + y;
			if (isValid(maze, nextX, nextY)) {
				maze[y][x] = P;
				if (findPath(maze, move[0] + x, move[1] + y)) return true;
				maze[y][x] = W;
			}
		}

		return false;
	}

	private static final boolean isValid(char[][] maze, int x, int y) {
		return (x >= 0) && (x < maze.length) && (y >= 0) && (y < maze.length) && !(maze[y][x] == P) && !(maze[y][x] == B);
	}

	public static void main(String[] args) {
		char[][] maze = new char[][]{
			{B, W, W, W, W, W, B, B, W, E},
			{W, W, B, W, W, W, W, W, W, W},
			{B, W, B, W, W, B, B, W, B, B},
			{W, W, W, B, B, B, W, W, B, W},
			{W, B, B, W, W, W, W, W, W, W},
			{W, B, B, W, W, B, W, B, B, W},
			{W, W, W, W, B, W, W, W, W, W},
			{B, W, B, W, B, W, W, W, W, W},
			{B, W, B, B, W, W, W, B, B, B},
			{S, W, W, W, W, W, W, B, B, W}
		};

		for (char[] line : maze) System.out.println(Arrays.toString(line));

		findPath(maze, 0, 9);

		System.out.println();

		for (char[] line : maze) System.out.println(Arrays.toString(line));
	}
}