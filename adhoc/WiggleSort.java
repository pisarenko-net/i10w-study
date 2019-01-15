import java.util.Arrays;
import java.util.Random;

/**
 * Re-order sequence such that numbers in odd positions are bigger or equals numbers surrounding them.
 */
public class WiggleSort {
	public static void wiggleSort(int[] a) {
		Arrays.sort(a);
		for (int i = 1; i < a.length - 1; i += 2) {
			exch(a, i, i+1);
		}
	}

	private static void exch(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		int[] input = new int[]{1,2,2,1,2,1,1,1,1,3,2,2};
		wiggleSort(input);
		System.out.println(Arrays.toString(input));
	}
}