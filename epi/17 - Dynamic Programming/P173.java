/**
 * Compute all possible ways to traverse a 2D array.
 */
public class P173 {
	public static int countWays(int n, int m) {
		int[][] array = new int[n][m];

		for (int i = 0; i < n; i++) array[i][0] = 1;
		for (int j = 0; j < m; j++) array[0][j] = 1;

		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				array[i][j] = array[i-1][j] + array[i][j-1];
			}
		}

		return array[n-1][m-1];
	}

	public static void main(String[] args) {
		System.out.println(countWays(5, 5));
	}
}