import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;

public class FindLongestWord {
	public static String findLongestWord(String s, List<String> d) {
		Collections.sort(d, new Comparator<String>() {
			public int compare(String a, String b) {
				return a.length() != b.length() ? Integer.compare(b.length(), a.length()) : a.compareTo(b);
			}
		});

		for (String word : d) {
			if (isContainedIn(word, s)) return word;
		}

		return "";
	}

	private static boolean isContainedIn(String arg, String target) {
		int targetIndex = 0, argIndex = 0;

		while (targetIndex < target.length()) {
 			if (arg.charAt(argIndex) == target.charAt(targetIndex)) {
				argIndex++;
				if (argIndex == arg.length()) return true;
			}
			targetIndex++;
		}

		return false;
	}

	public static void main(String[] args) {
		// System.out.println(isContainedIn("apple", "abpcplea"));
		// System.out.println(isContainedIn("aaa", "aaa"));
		System.out.println(findLongestWord("abpcplea", new ArrayList<>(Arrays.asList("bbb", "abe", "ale","apple","monkey","plea"))));
		System.out.println(findLongestWord("abpcplea", Arrays.asList("a", "b", "c")));
		System.out.println(findLongestWord("aaa", Arrays.asList("aaa", "aa", "a")));
		System.out.println(findLongestWord("wordgoodgoodgoodbestword", Arrays.asList("word","good","best","good")));
	}
}