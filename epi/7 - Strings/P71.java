/**
 * Convert integer to string and vice versa.
 */
public class P71 {
	private final static int DASH_ASCII_VALUE = 45;
	private final static char DIGIT_ASCII_DELTA = 48;

	public static int stringToInt(String input) {
		int length = input.length(), sum = 0, endIndex = 0;
		boolean isNegative = false;

		if (input.charAt(0) == DASH_ASCII_VALUE) {
			isNegative = true;
			endIndex = 1;
		}

		for (int i = endIndex; i < length; i++) {
			sum = sum * 10 + (input.charAt(i) - DIGIT_ASCII_DELTA);
		}

		return isNegative ? -sum : sum;
	}

	public static String intToString(int val) {
		boolean isNegative = false;

		if (val < 0) {
			isNegative = true;
			val = Math.abs(val);
		}

		int length = (int) Math.ceil(Math.log10(val));
		char[] string = new char[isNegative ? length + 1 : length];

		if (isNegative) {
			string[0] = '-';
		}

		for (int i = isNegative ? length : length - 1; i > 0; i--) {
			int digit = val % 10;
			string[i] = (char) (digit + DIGIT_ASCII_DELTA);
			val /= 10;
		}

		return new String(string);
	}

	public static void main(String[] args) {
		System.out.println(stringToInt("-1234"));
		System.out.println(intToString(8653));
	}
}