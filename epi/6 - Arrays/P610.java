import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Apply a permutation to an array.
 */
public class P610 {
	public static void permute(List A, List<Integer> P) {
		for (int i = 0; i < A.size(); i++) {
			while (P.get(i) != i) {
				Collections.swap(A, P.get(i), i);
				Collections.swap(P, P.get(i), i);
			}
		}
	}

	public static void main(String[] args) {
		List<Character> A = Arrays.asList('a', 'b', 'c', 'd');
		List<Integer> P = Arrays.asList(3, 2, 1, 0);

		permute(A, P);

		System.out.println(A);
	}
}