import java.util.Arrays;

/**
 * Count number of characters in a string, sorted by character without using extra space.
 */
public class P143 {
	public static String countCharacters(String input) {
		char[] chars = input.toCharArray();
		Arrays.sort(chars);

		StringBuilder output = new StringBuilder();

		char curr = chars[0];
		int count = 1;
		for (int i = 1; i < chars.length; i++) {
			if (chars[i] != curr) {
				output.append("" + curr + count);
				count = 1;
				curr = chars[i];
			} else {
				count++;
			}
		}
		output.append("" + curr + count);

		return output.toString();
	}

	public static void main(String[] args) {
		String input = "eazzhbabhabzh";
		System.out.println(countCharacters(input));
	}
}