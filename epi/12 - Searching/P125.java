/**
 * Compute integer square root for a given integer argument (all positive).
 */
public class P125 {
	public static int squareRoot(int x) {
		int lo = 0, hi = x, result = 0;

		while (hi >= lo) {
			int mid = lo + (hi - lo) / 2;
			int square = mid * mid;
			if (square > x) {
				hi = mid - 1;
			} else {
				result = mid;
				lo = mid + 1;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 300; i++) {
			System.out.println(i + ": " + squareRoot(i));
		}
	}
}