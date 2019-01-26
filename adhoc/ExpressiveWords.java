import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/expressive-words/
 */
public class ExpressiveWords {
	private static class CharGroup {
		int count;
		char c;
		boolean isExtended;

		CharGroup(int count, char c) {
			this.count = count;
			this.c = c;
			this.isExtended = count >= 3;
		}
	}

	public static int expressiveWords(String s, String[] words) {
		List<CharGroup> canonical = getRLE(s);

		int result = 0;

		for (String word : words) {
			if (canBeExtended(getRLE(word), canonical)) result++;
		}

		return result;
	}

	private static List<CharGroup> getRLE(String s) {
		List<CharGroup> rle = new ArrayList<>();

		char prev = s.charAt(0);
		int count = 1, i = 1;

		do {
			char curr = i < s.length() ? s.charAt(i) : '\0';
			if (curr == prev) count++;
			else {
				rle.add(new CharGroup(count, prev));
				count = 1;
				prev = curr;
			}
			i++;
		} while (i <= s.length());

		return rle;
	}

	private static boolean canBeExtended(List<CharGroup> arg, List<CharGroup> canonical) {
		if (canonical.size() != arg.size()) return false;

		for (int i = 0; i < canonical.size(); i++) {
			CharGroup canonicalGroup = canonical.get(i);
			CharGroup argGroup = arg.get(i);

			if ((argGroup.c != canonicalGroup.c) ||
				(argGroup.count > canonicalGroup.count) ||
				(argGroup.count < canonicalGroup.count && !canonicalGroup.isExtended)) return false;
		}

		return true;
	}

	public static void main(String[] args) {
		System.out.println(expressiveWords("heeellooo", new String[]{"helo", "hi", "hello", "heelloo"}));
		System.out.println(expressiveWords("zzzzzyyyyy", new String[]{"zzyy","zy","zyy"}));
		System.out.println(expressiveWords("le", new String[]{"lee"}));
		System.out.println(expressiveWords("abcd", new String[]{"abc"}));
	}
}