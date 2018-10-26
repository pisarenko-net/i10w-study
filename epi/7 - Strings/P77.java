import java.util.ArrayList;
import java.util.List;

/**
 * Compute all possible word mnemonics for a given phone number.
 */
public class P77 {
	private static String[] MAPPINGS = new String[]{"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

	public static List<String> computePhoneMnemonics(String phoneNumber) {
		List<String> results = new ArrayList<>();
		computePhoneMnemonics(phoneNumber, new char[phoneNumber.length()], 0, results);
		return results;
	}

	private static void computePhoneMnemonics(String phoneNumber, char[] result, int i, List<String> results) {
		if (i == phoneNumber.length()) {
			results.add(new String(result));
			return;
		}
		String mapping = MAPPINGS[phoneNumber.charAt(i) - '0'];
		for (int j = 0; j < mapping.length(); j++) {
			result[i] = mapping.charAt(j);
			computePhoneMnemonics(phoneNumber, result, i+1, results);
		}
	}

	public static void main(String[] args) {
		System.out.println(computePhoneMnemonics("23"));
	}
}