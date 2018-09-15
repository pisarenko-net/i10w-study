import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Sort an almost sorted array: elements are not more than k positions away from their sorted place.
 */
public class P113 {
	public static <T extends Comparable<T>> void sort(T[] a, int k) {
		PriorityQueue<T> pq = new PriorityQueue<>();
		for (int i = 0; i < k+1; i++) pq.add(a[i]);

		for (int i = 0; i < a.length; i++) {
			a[i] = pq.remove();
			if (i+k+1 < a.length) pq.add(a[i+k+1]);
		}
	}

	public static void main(String[] args) {
		Integer[] input = new Integer[]{3, -1, 2, 6, 4, 5, 8};
		sort(input, 2);
		System.out.println(Arrays.toString(input));
	}
}