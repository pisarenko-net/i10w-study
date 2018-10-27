import java.util.HashMap;
import java.util.Map;

/**
 * Finds distances to closest pairs of words.
 */
public class P137 {
	public static Map<String, Integer> findClosestPairs(String[] input) {
		Map<String, Integer> occurrences = new HashMap<>();
		Map<String, Integer> distances = new HashMap<>();
		for (int i = 0; i < input.length; i++) {
			String word = input[i].toLowerCase();
			if (occurrences.containsKey(word)) {
				distances.put(word, i - occurrences.get(word));
			}
			occurrences.put(word, i);
		}
		return distances;
	}

	public static void main(String[] args) {
		String[] input = new String[]{
			"All", "work", "and", "no", "play", "makes", "for", "no", "work", "no", "fun", "and", "no", "results"
		};

		Map<String, Integer> distances = findClosestPairs(input);
		for (String word : distances.keySet()) {
			System.out.println(word + " " + distances.get(word));
		}
	}
}