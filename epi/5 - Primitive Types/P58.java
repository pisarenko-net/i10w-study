/**
 * Reverse digits in an integral number.
 */
public class P58 {
	public static long reverseDigits(long x) {
		boolean isNegative = x < 0;
		long xRemaining = Math.abs(x), result = 0;

		while (xRemaining != 0) {
			result = result * 10 + (xRemaining % 10);
			xRemaining /= 10;
		}

		if (isNegative) result *= -1;

		return result;
	}

	public static void main(String[] args) {
		System.out.println(reverseDigits(-45623));
	}
}