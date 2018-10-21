/**
 * Compute n choose k: ways to choose k elements from a set of n elements.
 */
public class P174 {
	public static int getNChooseK(int n, int k) {
		int[][] coefficients = new int[n+1][k+1];
		for (int i = 0; i <= k; i++) coefficients[i][i] = 1;
		for (int i = 0; i < n; i++) coefficients[i][0] = 1;

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				if (coefficients[i][j] == 0) coefficients[i][j] = coefficients[i-1][j] + coefficients[i-1][j-1];
			}
		}

		return coefficients[n][k];
	}

	public static void main(String[] args) {
		System.out.println(getNChooseK(7, 4));
	}
}