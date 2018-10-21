import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * Check if a given sequence is present in a 2D array.
 */
public class P175 {
	@AllArgsConstructor
	@EqualsAndHashCode
	private static class DoomedSearch {
		int x, y, offset;
	}

	public static boolean containsSequence(int[][] array, int[] sequence) {
		Set<DoomedSearch> doomed = new HashSet<>();
		return containsSequence(array, sequence, doomed, 0, 0, 0);
	}

	private static boolean containsSequence(int[][] array, int[] sequence, Set<DoomedSearch> doomed, int x, int y, int offset) {
		if (offset == sequence.length) return true;

		DoomedSearch attempt = new DoomedSearch(x, y, offset);
		if (x < 0 || y < 0 || x >= array.length || y >= array[x].length || doomed.contains(attempt)) return false;

		if (array[x][y] == sequence[offset]) {
			if (containsSequence(array, sequence, doomed, x+1, y, offset+1) ||
				containsSequence(array, sequence, doomed, x-1, y, offset+1) ||
				containsSequence(array, sequence, doomed, x, y+1, offset+1) ||
				containsSequence(array, sequence, doomed, x, y-1, offset+1)) return true;
		}

		doomed.add(attempt);
		return false;
	}

	public static void main(String[] args) {
		int[][] array = new int[][]{
			{1, 2, 3},
			{3, 4, 5},
			{5, 6, 7}
		};
		int[] sequence = new int[]{1, 3, 4, 6};

		System.out.println(containsSequence(array, sequence));
	}
}