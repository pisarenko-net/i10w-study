import java.lang.StringBuilder;

/**
 * Reverse the order of words in a string.
 */
public class P76 {
	public static String reverseWords(String words) {
		StringBuilder sb = new StringBuilder(words);
		sb.reverse();

		int start = -1, end = -1;
		for (int i = 0; i < sb.length(); i++) {
			if (Character.isLetterOrDigit(sb.charAt(i))) {
				if (start == -1) {
					start = i;
					end = i;
				} else {
					end++;
				}
			} else if (start != -1) {
				reverseWord(sb, start, end);
				start = end = -1;
			}
		}

		if (start != -1) reverseWord(sb, start, end);

		return sb.toString();
	}

	private static void reverseWord(StringBuilder sb, int start, int end) {
		int length = end - start;
		for (int i = 0; i <= length / 2; i++) {
			char t = sb.charAt(start + i);
			sb.setCharAt(start + i, sb.charAt(end - i));
			sb.setCharAt(end - i, t);
		}
	}

	public static void main(String[] args) {
		String input = "Alice likes Bob";
		System.out.println(reverseWords(input));
	}
}