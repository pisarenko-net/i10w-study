/**
 * Compute continuous subarray with highest sum of elements.
 */
public class P170 {
	private static class Subarray {
		int start;
		int end;

		Subarray(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static Subarray findMaxSubarray(int[] a) {
		Subarray solution = new Subarray(0, 0);
		int minIdx = -1, minSum = 0, sum = 0, maxSum = 0;

		for (int i = 0; i < a.length; i++) {
			sum += a[i];
			if (sum < minSum) {
				minSum = sum;
				minIdx = i;
			}

			if (sum - minSum > maxSum) {
				maxSum = sum - minSum;
				solution = new Subarray(minIdx + 1, i + 1);
			}
		}

		return solution;
	}

	public static void main(String[] args) {
		int[] input = new int[]{904, 40, 523, 12, -335, -385, -124, 481, -31};
		Subarray solution = findMaxSubarray(input);
		System.out.println("[" + solution.start + ", " + solution.end + ")");
	}
}