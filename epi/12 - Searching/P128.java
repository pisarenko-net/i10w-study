/**
 * Find max and min while minimizing comparisons.
 */
public class P128 {
	private static class MinMax {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
	}

	public static MinMax findMinMax(int[] input) {
		MinMax curr = new MinMax();

		for (int i = 0; i + 1 < input.length; i += 2) {
			if (input[i+1] > input[i]) {
				curr.max = Math.max(input[i+1], curr.max);
				curr.min = Math.min(input[i], curr.min);
			} else {
				curr.max = Math.max(input[i], curr.max);
				curr.min = Math.min(input[i+1], curr.min);
			}
		}

		if (input.length % 2 == 1) {
			curr.max = Math.max(input[input.length-1], curr.max);
			curr.min = Math.min(input[input.length-1], curr.min);
		}

		return curr;
	}

	public static void main(String[] args) {
		MinMax mm = findMinMax(new int[]{3, 2, 5, 1, 2, 4});
		System.out.println(mm.min);
		System.out.println(mm.max);
	}
}