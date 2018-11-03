import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a string, return decomposition into words, if possible.
 */
public class P177 {
	public static Iterable<String> decomposition(String input, Set<String> dictionary) {
		int[] lastLengths = new int[input.length()];
		Arrays.fill(lastLengths, -1);

		for (int i = 0; i < input.length(); i++) {
			if (dictionary.contains(input.substring(0, i+1))) {
				lastLengths[i] = i + 1;
			}

			if (lastLengths[i] == -1) {
				for (int j = 0; j < i; j++) {
					if ((lastLengths[j] != -1) && (dictionary.contains(input.substring(j+1, i+1)))) {
						lastLengths[i] = i - j;
						break;
					}
				}
			}
		}

		List<String> decomposition = new ArrayList<>();
		int idx = lastLengths.length - 1;
		if (lastLengths[idx] != -1) {
			while (idx >= 0) {
				decomposition.add(input.substring(idx + 1 - lastLengths[idx], idx + 1));
				idx -= lastLengths[idx];
			}
			Collections.reverse(decomposition);
		}
		return decomposition;
	}

	public static void main(String[] args) {
		Set<String> dictionary = new HashSet<>(Arrays.asList(
			"bad",
			"bath",
			"and",
			"beyond"
			));

		for (String word : decomposition("badbathandbeyond", dictionary)) {
			System.out.println(word);
		}
	}
}