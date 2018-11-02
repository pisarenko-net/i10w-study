import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import lombok.*;

/**
 * Given k sorted arrays find a list of k integers, one from each array, minimizing the distance (max-min).
 */
public class P157 {
	@AllArgsConstructor
	@ToString
	private static class Candidate implements Comparable<Candidate> {
		int arrayIndex;
		int index;
		int value;

		public int compareTo(Candidate that) {
			int cmp = Integer.compare(this.value, that.value);
			return cmp != 0 ? cmp : Integer.compare(this.hashCode(), that.hashCode());
		}
	}

	public static List<Integer> smallest(List<List<Integer>> arrays) {
		TreeSet<Candidate> curr = new TreeSet<>();
		int arrayIndex = 0;
		for (List<Integer> array : arrays) {
			curr.add(new Candidate(arrayIndex++, 0, array.get(0)));
		}

		int smallest = curr.last().value - curr.first().value;
		TreeSet<Candidate> best = new TreeSet<>(curr);

		while (true) {
			Candidate min = curr.first();
			if (min.index == arrays.get(min.arrayIndex).size() - 1) break;
			Candidate next = new Candidate(
				min.arrayIndex,
				min.index+1,
				arrays.get(min.arrayIndex).get(min.index+1)
			);
			curr.remove(min);
			curr.add(next);
			int delta = curr.last().value - curr.first().value;
			if (delta < smallest) {
				smallest = delta;
				best = new TreeSet<>(curr);
			}
		}

		List<Integer> result = new ArrayList<>();
		for (Candidate c : best) {
			result.add(c.value);
		}

		return result;
	}

	public static void main(String[] args) {
		List<List<Integer>> input = Arrays.asList(
			Arrays.asList(5, 10, 15),
			Arrays.asList(3, 6, 9, 12, 15),
			Arrays.asList(8, 16, 24)
			);

		for (Integer i : smallest(input)) System.out.println(i);
	}
}