/**
 * 0/1 Knapsack problem.
 */
public class P176 {
	public static int maximizeGain(int[] values, int[] weights, int capacity) {
		int[][] solution = new int[weights.length+1][capacity+1];

		for (int i = 0; i < weights.length; i++) {
			int weight = weights[i], value = values[i];

			for (int c = 0; c <= capacity; c++) {
				solution[i+1][c] = Math.max(
					solution[i][c],
					c >= weight ? value + solution[i][c - weight] : 0
				);
			}
		}

		return solution[weights.length][capacity];
	}

	public static void main(String[] args) {
		int[] values = new int[]{65, 35, 245, 195, 65, 150, 275, 155, 120, 320, 75, 40, 200, 100, 220, 99};
		int[] weights = new int[]{20, 8, 60, 55, 40, 70, 85, 25, 30, 65, 75, 10, 95, 50, 40, 10};
		int capacity = 130;

		// int[] values = new int[]{60, 50, 70, 30};
		// int[] weights = new int[]{5, 3, 4, 2};
		// int capacity = 5;

		System.out.println(maximizeGain(values, weights, capacity));
	}
}