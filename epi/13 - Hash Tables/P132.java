import java.util.HashMap;
import java.util.Map;

/**
 * Check if a given string can be made into a palindromic string.
 */
public class P132 {
	public static boolean isPalindromePossible(String word) {
		Map<Character, Integer> charCounts = new HashMap<>();
		for (char c : word.toCharArray()) {
			if (!charCounts.containsKey(c)) {
				charCounts.put(c, 1);
			} else {
				charCounts.put(c, charCounts.get(c)+1);
			}
		}

		boolean oddNumberOfCharactersSeen = false;
		for (char c : charCounts.keySet()) {
			int count = charCounts.get(c);
			if (count % 2 != 0) {
				if (word.length() % 2 != 0) {
					if (!oddNumberOfCharactersSeen) {
						oddNumberOfCharactersSeen = true;
						continue;
					}
				}
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		System.out.println(isPalindromePossible("deifiedz"));
	}
}