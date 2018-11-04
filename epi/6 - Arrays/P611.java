import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Compute the next permutation.
 */
public class P611 {
	public static void permuteNext(List<Integer> input) {
		int k = input.size() - 2;
		while (k >= 0 && input.get(k) > input.get(k+1)) k--;

		if (k == -1) {
			input.clear();
			return;
		}

		int j = input.size() - 1;
		while (input.get(j) < input.get(k)) j--;

		Collections.swap(input, k, j);
		Collections.reverse(input.subList(k + 1, input.size()));
	}

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
		while (!input.isEmpty()) {
			System.out.println(input);
			permuteNext(input);
		}
	}
}