import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;

/**
 * Generate all possible permutations.
 */
public class P163 {
	public static List<List<Integer>> permutations(List<Integer> input) {
		List<List<Integer>> result = new ArrayList<>();
		computePermutations(0, new ArrayList<>(input), result);
		return result;
	}

	private static void computePermutations(int i, List<Integer> input, List<List<Integer>> result) {
		if (i == (input.size()-1)) {
			result.add(new ArrayList<>(input));
			return;
		}

		for (int j = i; j < input.size(); j++) {
			Collections.swap(input, i, j);
			computePermutations(i+1, input, result);
			Collections.swap(input, i, j);
		}
	}

	public static void main(String[] args) {
		for (List<Integer> permutation : permutations(Arrays.asList(1, 2, 6))) {
			System.out.println(permutation);
		}
	}
}