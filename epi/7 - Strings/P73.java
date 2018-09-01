/**
 * Convert spreadsheet column names (e.g. A, B, C ... X, Y, Z, AA, AB, AC ... ZZ, AAA) to decimal numbers.
 */
public class P73 {
	private static final int BASE = 26;

	public static long columnToNumber(String columnName) {
		long sum = 0;

		for (int i = columnName.length() - 1; i >= 0; i--) {
			int letterValue = letterToNumber(columnName.charAt(i));
			sum += letterValue * Math.pow(BASE, columnName.length() - i - 1);
		}

		return sum;
	}

	private static int letterToNumber(char letter) {
		return Character.digit(letter, 36) - 9;
	}

	public static void main(String[] args) {
		System.out.println(columnToNumber("ZZ"));
	}
}