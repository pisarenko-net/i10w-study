/**
 * Search a sorted array of distinct values for a fixed point (value equals element's index).
 */
public class P123 {
	public static int rank(int[] input) {
		int lo = 0, hi = input.length - 1;

		while (hi >= lo) {
			int mid = lo + (hi - lo) / 2;
			int value = input[mid];
			if (value == mid) return mid;
			else if (mid < value) hi = mid - 1;
			else lo = mid + 1;
		}

		return -1;
	}

	public static void main(String[] args) {
		int[] input = new int[]{-2, 0, 2, 4, 6, 7, 9};
		System.out.println(rank(input));
	}
}