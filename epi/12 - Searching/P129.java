import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Find kth largest element in an array.
 */
public class P129 {
	public static int findKthLargest(int[] input, int k) {
		int lo = 0, hi = input.length - 1, mid;
		k--;

		while (true) {
			mid = pivot(input, lo, hi);

			if (mid < k) {
				lo = mid + 1;
			} else if (mid > k) {
				hi = mid - 1;
			} else {
				return input[k];
			}
		}
	}

	private static int pivot(int[] input, int lo, int hi) {
		int v = input[lo];
		int i = lo + 1;

		while (i <= hi) {
			int cmp = Integer.compare(input[i], v);
			if (cmp < 0) {
				exch(input, i++, lo++);
			} else if (cmp > 0) {
				exch(input, i, hi--);
			} else i++;
		}

		return i-1;
	}

	private static void exch(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static void main(String[] args) {
		int[] input = new Random().ints(20, 0, 100).toArray();
		System.out.println(Arrays.toString(input));
		System.out.println(findKthLargest(input, 13));

		Arrays.sort(input);
		System.out.println(Arrays.toString(input));
	}
}