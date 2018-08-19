import java.util.Arrays;
import java.util.Random;

/**
 * Given an index, rearrange the array so that elements smaller than a[i] appear before it
 * and elements bigger than a[i] appear after it.
 */
public class P61 {
	public static <T extends Comparable<T>> void partition(T[] a, int pivot) {
		int lt = 0, i = pivot, gt = a.length - 1;
		T val = a[i];

		while (i <= gt) {
			int cmp = a[i].compareTo(val);
			if (cmp < 0) exch(a, lt++, i++);
			else if (cmp > 0) exch(a, gt--, i);
			else i++;
		}
	}

	private static <T extends Comparable<T>> void exch(T[] a, int i, int j) {
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		Integer[] input = new Random().ints(20, 1, 4).boxed().toArray(Integer[]::new);
		input[0] = 2;
		System.out.println(Arrays.toString(input));

		partition(input, 0);
		System.out.println(Arrays.toString(input));
	}
}