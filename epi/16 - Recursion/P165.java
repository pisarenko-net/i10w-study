import java.util.ArrayList;
import java.util.List;

/**
 * Generate all subsets of k out of n.
 */
public class P165 {
	public static List<List<Integer>> subsets(int n, int k) {
		List<List<Integer>> result = new ArrayList<>();
		subsets(n, k, 1, new ArrayList<>(), result);
		return result;
	}

	private static void subsets(int n, int k, int offset, List<Integer> solution, List<List<Integer>> result) {
		if (solution.size() == k) {
			result.add(new ArrayList<>(solution));
			return;
		}

		for (int i = offset; i <= n; i++) {
			solution.add(i);
			subsets(n, k, i + 1, solution, result);
			solution.remove(solution.size() - 1);
		}
	}

	public static void main(String[] args) {
		for (List<Integer> solution : subsets(5, 2)) {
			System.out.println(solution);
		}
	}
}