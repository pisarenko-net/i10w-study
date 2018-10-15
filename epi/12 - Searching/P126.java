/**
 * Compute square root of a real number.
 */
public class P126 {
	private static final double EPSILON = 0.00001;

	public static double squareRoot(double x) {
		double lo, hi;
		if (x < 1.0) {
			lo = x;
			hi = 1.0;
		} else {
			lo = 1.0;
			hi = x;
		}

		while (compare(lo, hi) < 0) {
			double mid = lo + (hi - lo) / 2;
			double squared = mid * mid;
			int cmp = compare(squared, x);
			if (cmp < 0) {
				lo = mid;
			} else if (cmp > 0) {
				hi = mid;
			} else {
				return mid;
			}
		}

		return lo;
	}

	private static int compare(double a, double b) {
		double diff = (a - b) / b;
		return diff < -EPSILON ? -1 : diff > EPSILON ? 1 : 0;
	}

	public static void main(String[] args) {
		System.out.println(squareRoot(141));
	}
}