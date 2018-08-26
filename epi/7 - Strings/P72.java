/**
 * Convert a string given in one base into another base. 2 <= b1, b2 <= 16.
 */
public class P72 {
	public static String convertBase(String input, int sourceBase, int targetBase) {
		return convertIntToString(convertStringToDecimal(input, sourceBase), targetBase);
	}

	private static int convertStringToDecimal(String input, int base) {
		int sum = 0;
		int length = input.length();

		for (int i = 0; i < length; i++) {
			sum += Character.getNumericValue(input.charAt(i)) * ((int) Math.pow(base, length - i - 1));
		}

		return sum;
	}

	private static String convertIntToString(int value, int base) {
		StringBuffer result = new StringBuffer();

		while (value != 0) {
			result.append(Character.forDigit(value % base, base));
			value /= base;
		}

		return result.reverse().toString();
	}

	public static void main(String[] args) {
		//System.out.println(convertStringToDecimal("12", 10));
		//System.out.println(convertIntToString(12, 3));
		System.out.println(convertBase("615", 7, 13));  // 1a7
		System.out.println(convertBase("1a7", 13, 7));  // 615
	}
}