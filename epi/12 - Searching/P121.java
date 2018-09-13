/**
 * Find first occurrence of k in a sorted array.
 */
public class P121 {
	public static int search(int[] a, int key) {
		int lo = 0, hi = a.length - 1, result = 0;

		while (hi >= lo) {
			int mid = lo + (hi - lo) / 2;
			if (key <= a[mid]) {
				if (a[mid] == key) return mid;
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		int[] input = new int[]{-14, -10, 0, 2, 6, 6, 108, 108, 256, 999, 999, 999, 1024};
		System.out.println(search(input, 6));
	}
}