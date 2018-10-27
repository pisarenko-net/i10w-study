import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import lombok.AllArgsConstructor;

public class P138 {
	@AllArgsConstructor
	private static class Subarray {
		int start;
		int end;

		int length() {
			return end - start;
		}
	}

	public static Subarray findShortestContext(String[] input, Set<String> query) {
		Subarray shortest = null;
		LinkedHashMap<String, Integer> indices = new LinkedHashMap<>();

		for (int i = 0; i < input.length; i++) {
			String word = input[i].toLowerCase();
			if (query.contains(word)) {
				indices.remove(word);
				indices.put(word, i);

				if (indices.size() == query.size()) {
					int firstIndex = indices.entrySet().iterator().next().getValue();
					if ((shortest == null) || (shortest != null && shortest.length() > (i-firstIndex))) {
						shortest = new Subarray(firstIndex, i);
					}
				}
			}
		}

		return shortest;
	}

	public static void main(String[] args) {
		String text = "My paramount object in this struggle is to save the Union and is not either to save or to destroy slavery " +
		"If could save the Union without freeing any slave I would do it and if I could save it by freeing all the " +
		"slaves I would it and if I could save it by freeing some and leaving others alone I would also do it";

		String[] input = text.split("\\s");
		Set<String> query = new HashSet<>(Arrays.asList("save", "union"));

		Subarray context = findShortestContext(input, query);
		System.out.println(context.start + " : " + context.end);
	}
}