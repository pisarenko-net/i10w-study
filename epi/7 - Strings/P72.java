import java.util.Map;
import com.google.common.collect.ImmutableMap;

/**
 * Convert a string given in one base into another base. 2 <= b1, b2 <= 16.
 */
public class P72 {
	private final static Map<Integer, Character> digits = ImmutableMap.<Integer, Character>builder()
		.put(0, '0')
		.put(1, '1')
		.put(2, '2')
		.put(3, '3')
		.put(4, '4')
		.put(5, '5')
		.put(6, '6')
		.put(7, '7')
		.put(8, '8')
		.put(9, '9')
		.put(10, 'A')
		.put(11, 'B')
		.put(12, 'C')
		.put(13, 'D')
		.put(14, 'E')
		.put(15, 'F')
		.build();

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
			result.append(digits.get(value % base));
			value /= base;
		}

		return result.reverse().toString();
	}

	public static void main(String[] args) {
		//System.out.println(convertStringToDecimal("12", 10));
		//System.out.println(convertIntToString(12, 3));
		System.out.println(convertBase("615", 7, 13));
	}
}