import java.util.ArrayList;
import java.util.List;

/**
 * Generate all valid combinations of n parens pairs.
 */
public class P166 {
	public static List<String> parens(int pairs) {
		List<String> result = new ArrayList<>();
		parens(pairs, pairs, "", result);
		return result;
	}

	private static void parens(int leftParensNeeded, int rightParensNeeded, String solution, List<String> result) {
		if (leftParensNeeded == 0 && rightParensNeeded == 0) {
			result.add(solution);
			return;
		}

		if (leftParensNeeded > 0) parens(leftParensNeeded-1, rightParensNeeded, solution + "(", result);
		if (leftParensNeeded < rightParensNeeded) parens(leftParensNeeded, rightParensNeeded-1, solution + ")", result);
	}

	public static void main(String[] args) {
		for (String solution : parens(3)) {
			System.out.println(solution);
		}
	}
}