import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Given a set of words, identify groups of anagrams.
 */
public class P131 {
	public static Iterable<List<String>> groupAnagrams(List<String> words) {
		Map<String, List<String>> groups = new HashMap<>();

		for (String word : words) {
			String canonical = toCanonicalForm(word);
			if (!groups.containsKey(canonical)) {
				groups.put(canonical, new ArrayList<>());
			}
			groups.get(canonical).add(word);
		}

		List<List<String>> result = new ArrayList<>();
		for (String canonical : groups.keySet()) {
			if (groups.get(canonical).size() > 1) {
				result.add(groups.get(canonical));
			}
		}

		return result;
	}

	private static String toCanonicalForm(String word) {
		char[] characters = word.toCharArray();
		Arrays.sort(characters);
		return new String(characters);
	}

	public static void main(String[] args) {
		for (List<String> group : groupAnagrams(Arrays.asList("debitcard", "elvis", "silent", "badcredit", "lives", "freedom", "listen", "levis", "money"))) {
			for (String word : group) {
				System.out.print(word + " ");
			}
			System.out.println();
		}
	}
}