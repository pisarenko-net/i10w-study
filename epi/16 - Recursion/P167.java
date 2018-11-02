import java.util.ArrayList;
import java.util.List;

/**
 * Compute all possible palindromic decompositions of a string.
 */
public class P167 {
	public static List<List<String>> getPalindromicDecompositions(String input) {
		List<List<String>> solutions = new ArrayList<>();
		computePalindromicDecompositions(input, 0, new ArrayList<>(), solutions);
		return solutions;
	}

	private static void computePalindromicDecompositions(String input, int start, List<String> solution, List<List<String>> solutions) {
		if (start == input.length()) {
			solutions.add(new ArrayList<>(solution));
			return;
		}

		for (int end = start; end < input.length(); end++) {
			if (isPalindrome(input, start, end)) {
				solution.add(input.substring(start, end+1));
				computePalindromicDecompositions(input, end + 1, solution, solutions);
				solution.remove(solution.size() - 1);
			}
		}
	}

	private static boolean isPalindrome(String input, int start, int end) {
		if (start == end) return true;

		int length = end - start + 1;
		for (int i = 0; i < length/2; i++) {
			if (input.charAt(start + i) != input.charAt(end - i)) return false;
		}
		return true;
	}

	public static void main(String[] args) {
		for (List<String> decomposition : getPalindromicDecompositions("0204451881")) {
			for (String s : decomposition)
				System.out.print(s + " ");
			System.out.println();
		}
	}
}