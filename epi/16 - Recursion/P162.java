import java.util.ArrayList;
import java.util.List;

/**
 * Compute all possible nonattacking placements of queen on an n*n grid.
 */
public class P162 {
	public static List<List<Integer>> queenPlacements(int n) {
		List<List<Integer>> results = new ArrayList<>();
		putQueens(n, 0, new ArrayList(), results);
		return results;
	}

	private static void putQueens(int n, int currentRow, List<Integer> solution, List<List<Integer>> results) {
		if (currentRow == n) {
			results.add(new ArrayList<>(solution));
			return;
		}

		for (int col = 0; col < n; col++) {
			solution.add(col);
			if (isValid(solution)) {
				putQueens(n, currentRow+1, solution, results);
			}
			solution.remove(solution.size()-1);
		}
	}

	private static boolean isValid(List<Integer> solution) {
		int row = solution.size() - 1;
		for (int i = 0; i < row; i++) {
			int diff = Math.abs(solution.get(i) - solution.get(row));
			if (diff == 0 || diff == (row - i)) return false;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(queenPlacements(Integer.parseInt(args[0])));
	}
}