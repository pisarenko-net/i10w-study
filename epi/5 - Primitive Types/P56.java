/**
 * Compute division of two positive integral numbers (quotient).
 */
public class P56 {
	public static long divide(long x, long y) {
		long result = 0;
		int power = 32;
		long yPower = y << 32;

		while (x >= y) {
			while (yPower > x) {
				yPower >>>= 1;
				power--;
			}
			result += 1L << power;
			x -= yPower;
		}

		return result;
	}

	public static void main(String[] args) {
		System.out.println(divide(341, 12));
	}
}