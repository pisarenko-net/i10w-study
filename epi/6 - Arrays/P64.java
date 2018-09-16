/**
 * Jump - advance through an at most A[i] steps forward. Is it possible to reach the end?
 */
public class P64 {
	public static boolean isEndReachable(int[] input) {
		int biggestSoFar = 0, last = input.length - 1;

		for (int i = 0; i <= biggestSoFar && biggestSoFar < last; i++) {
			biggestSoFar = Math.max(biggestSoFar, i + input[i]);
		}

		return biggestSoFar >= last;
	}

	public static void main(String[] args) {
		int[] input = new int[]{3, 3, 1, 0, 2, 0, 1};
		//int[] input = new int[]{3, 2, 0, 0, 2, 0, 1};
		System.out.println(isEndReachable(input));
	}
}