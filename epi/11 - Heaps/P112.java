import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Sort an increasing-decreasing sequence.
 */
public class P112 {
	private static class Subarray<T extends Comparable<T>> implements Comparable<Subarray<T>> {
		int start;
		int end;
		boolean isIncreasing;
		int offset = 0;
		T[] array;

		boolean hasNext() {
			return offset <= Math.abs(start - end);
		}

		T next() {
			T key = peek();
			offset++;
			return key;
		}

		T peek() {
			return isIncreasing ? array[start + offset] : array[end - offset];
		}

		Subarray(T[] array, boolean isIncreasing, int start, int end) {
			this.array = array;
			this.isIncreasing = isIncreasing;
			this.start = start;
			this.end = end;
		}

		public int compareTo(Subarray<T> other) {
			T a = this.peek();
			T b = other.peek();
			return a.compareTo(b);
		}
	}

	public static <T extends Comparable<T>> T[] sort(T[] a) {
		List<Subarray<T>> subarrays = findSubarrays(a);
		PriorityQueue<Subarray<T>> pq = new PriorityQueue<>();
		subarrays.forEach(sub -> pq.add(sub));

		T[] result = (T[]) new Comparable[a.length];
		int i = 0;

		while (!pq.isEmpty()) {
			Subarray<T> sub = pq.poll();
			result[i] = sub.next();
			if (sub.hasNext()) pq.add(sub);
			i++;
		}

		return result;
	}

	private static <T extends Comparable<T>> List<Subarray<T>> findSubarrays(T[] a) {
		List<Subarray<T>> subarrays = new ArrayList<>();

		boolean isIncreasing = a[0].compareTo(a[1]) < 0;
		int start = 0, end = 0;
		for (int i = 1; i < a.length; i++) {
			if ((isIncreasing && a[i].compareTo(a[i-1]) < 0) || (!isIncreasing && a[i].compareTo(a[i-1]) > 0)) {
				end = i-1;
				subarrays.add(new Subarray<T>(a, isIncreasing, start, end));
				isIncreasing = !isIncreasing;
				start = i;
			}
		}

		if (end != a.length-1) {
			subarrays.add(new Subarray<T>(a, isIncreasing, start, a.length-1));
		}

		return subarrays;
	}

	public static void main(String[] args) {
		Integer[] input = new Integer[]{57, 131, 493, 294, 221, 339, 418, 452, 442, 190};
		Integer[] check = Arrays.copyOf(input, 0);

		Arrays.sort(check);
		Object[] result = sort(input);

		System.out.println(Arrays.toString(result));
		System.out.println(Arrays.equals(check, result));
	}
}