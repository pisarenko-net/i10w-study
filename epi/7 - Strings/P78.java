/**
 * Compute nth string in the look-and-say sequence.
 */
public class P78 {
	public static String lookAndSay(int n) {
		StringBuilder sb = new StringBuilder("1");
		for (int i = 0; i < n; i++) sb = lookAndSay(sb);
		return sb.toString();
	}

	private static StringBuilder lookAndSay(StringBuilder input) {
		int count = 0;
		char prev = input.charAt(0);

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) != prev) {
				result.append("" + count + "" + prev);
				count = 1;
				prev = input.charAt(i);
			} else count++;
		}
		result.append("" + count + "" + prev);
		return result;
	}

	public static void main(String[] args) {
		System.out.println(lookAndSay(5));
	}
}