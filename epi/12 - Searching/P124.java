/**
 * Find the smallest element in a cyclically sorted array (i.e. sorted array shifted by k).
 */
public class P124 {
	public static int smallest(int[] input) {
		int lo = 0, hi = input.length - 1;

		while (hi > lo) {
			int mid = lo + (hi - lo) / 2;

			if (input[mid] > input[hi]) {
				lo = mid + 1;
			} else {
				hi = mid;
			}
		}

		return lo;
	}

	public static void main(String[] args) {
		int[] input = new int[]{378, 478, 550, 631, 972, 1024, 103, 203, 220};
		//int[] input = new int[]{378, 478, 550, 631, 103, 203, 220, 234, 279, 368};
		System.out.println(smallest(input));
	}
}