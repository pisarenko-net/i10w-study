import java.util.Arrays;

/**
 * Merge two sorted arrays in place, assuming first array is big enough to hold both.
 */
public class P142 {
	public static <T extends Comparable<T>> void merge(T[] bigger, T[] smaller) {
		int bIndex, sIndex = smaller.length - 1;
		for (bIndex = 0; bigger[bIndex+1] != null; bIndex++);
		int finalLength = bIndex + sIndex;
		for (int i = finalLength+1; i >= 0; i--) {
			if (bIndex < 0) bigger[i] = smaller[sIndex--];
			else if (sIndex < 0) bigger[i] = bigger[bIndex--];
			else if (bigger[bIndex].compareTo(smaller[sIndex]) < 0) bigger[i] = smaller[sIndex--];
			else bigger[i] = bigger[bIndex--];
		}
	}

	public static void main(String[] args) {
		Integer[] a = new Integer[]{5, 13, 17};
		Integer[] b = new Integer[]{3, 7, 11, 19};

		Integer[] aBig = new Integer[8];
		for (int i = 0; i < a.length; i++) aBig[i] = a[i];

		Integer[] check = new Integer[a.length + b.length];
		for (int i = 0; i < a.length; i++) check[i] = a[i];
		for (int i = 0; i < b.length; i++) check[a.length + i] = b[i];
		Arrays.sort(check);

		merge(aBig, b);
		System.out.println(Arrays.equals(aBig, check));
		System.out.println(Arrays.toString(aBig));
	}
}