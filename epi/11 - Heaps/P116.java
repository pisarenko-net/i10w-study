import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Find k largest elements in a binary heap without changing it.
 */
public class P116 {
	private static class HeapEntry implements Comparable<HeapEntry> {
		int value;
		int index;

		HeapEntry(int value, int index) {
			this.value = value;
			this.index = index;
		}

		public int compareTo(HeapEntry other) {
			return Integer.compare(this.value, other.value);
		}
	}

	public static Iterable<Integer> getLargest(int[] heap, int k) {
		List<Integer> result = new ArrayList<>();

		PriorityQueue<HeapEntry> pq = new PriorityQueue<>(100, Collections.reverseOrder());
		pq.add(new HeapEntry(heap[0], 1));

		while (result.size() < k) {
			HeapEntry entry = pq.remove();
			result.add(entry.value);

			int leftChildIndex = entry.index * 2;
			if (leftChildIndex - 1 < heap.length) {
				pq.add(new HeapEntry(heap[entry.index * 2 - 1], entry.index * 2));	
			}
			if (leftChildIndex < heap.length) {
				pq.add(new HeapEntry(heap[entry.index * 2], entry.index * 2 + 1));
			}
		}

		return result;
	}

	public static void main(String[] args) {
		int[] input = new int[]{516, 314, 401, 28, 156, 359, 271, 11, 3};
		for (int i : getLargest(input, 4)) {
			System.out.println(i);
		}
	}
}