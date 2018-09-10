import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import lombok.*;

/**
 * Merge/handle multiple sorted sequences.
 */
public class P111 {
	@AllArgsConstructor
	private static class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
		int sequence;
		T value;

		public int compareTo(Node<T> b) {
			return value.compareTo(b.value);
		}
	}

	public static <T extends Comparable<T>> Iterable<T> merge(Iterable<T> ... sequences) {
		PriorityQueue<Node> minPQ = new PriorityQueue<>();
		List<T> result = new ArrayList<>();

		Iterator<T>[] iterators = new Iterator[sequences.length];

		for (int i = 0; i < sequences.length; i++) {
			iterators[i] = sequences[i].iterator();
			if (iterators[i].hasNext()) {
				minPQ.add(new Node(i, iterators[i].next()));
			}
		}

		while (!minPQ.isEmpty()) {
			Node<T> n = minPQ.remove();
			result.add(n.value);
			if (iterators[n.sequence].hasNext()) {
				minPQ.add(new Node(n.sequence, iterators[n.sequence].next()));
			}
		}

		return result;
	}

	public static void main(String[] args) {
		List<Integer> seq1 = Arrays.asList(new Integer[]{3, 5, 7});
		List<Integer> seq2 = Arrays.asList(new Integer[]{0, 6});
		List<Integer> seq3 = Arrays.asList(new Integer[]{0, 6, 28});

		for (Integer i : merge(seq1, seq2, seq3)) {
			System.out.println(i);
		}
	}
}