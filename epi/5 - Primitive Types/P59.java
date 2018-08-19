/**
 * Checks if a number is a palindrome.
 */
public class P59 {
	public static boolean isPalindrome(long x) {
		if (x < 0) return false;
		if (x == 0) return true;

		int digits = (int) Math.ceil(Math.log10(x));
		long mask = (long) Math.pow(10, digits - 1);

		for (int i = 0; i < digits/2; i++) {
			if ((x % 10) != (x / mask)) {
				return false;
			}

			x %= mask;
			x /= 10;
			mask /= 100;
		}

		return true;
	}

	public static void main(String[] args) {
		System.out.println(isPalindrome(45654));
	}
}