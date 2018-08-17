/**
 * Compute x^y effectively.
  */
public class P57 {
	public static double exp(double x, int y) {
		double result = 1.0;
		int power = y;

		if (y < 0) {
			power = -power;
			x = 1.0 / x;
		}

		while (power > 0) {
			if ((power & 1) != 0) {
				result *= x;
			}

			x *= x;
			power >>>= 1;
		}

		return result;
	}

	public static void main(String[] args) {
		System.out.println(exp(2.1, 3));
	}
}