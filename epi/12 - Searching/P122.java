/**
 * Find the first element bigger than k in a sorted array.
  */
public class P122 {
	public static int search(int[] a, int k) {
		int lo = 0, hi = a.length - 1, result = -1;
		while (hi >= lo) {
			int mid = lo + (hi - lo) / 2;
			if (k < a[mid]) {
				result = a[mid];
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] input = new int[]{-14, -10, 2, 108, 108, 243, 285, 285, 401};
		System.out.println(search(input, 401));
	}
}