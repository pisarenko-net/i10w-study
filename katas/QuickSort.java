import java.util.Arrays;
import java.util.Random;
import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {
	public static <T extends Comparable<T>> void sort(T[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}

	private static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
		if (hi <= lo) return;

		int lt = lo, i = lo + 1, gt = hi;
		T v = a[lo];

		while (i <= gt) {
			int cmp = a[i].compareTo(v);
			if (cmp < 0) exch(a, lt++, i++);
			else if (cmp > 0) exch(a, gt--, i);
			else i++;
		}

		sort(a, lo, lt-1);
		sort(a, gt+1, hi);
	}

	private static <T extends Comparable<T>> void exch(T[] a, int i, int j) {
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		Integer[] input = new Random().ints(15, 0, 100).boxed().toArray(Integer[]::new);
		Integer[] check = Arrays.copyOf(input, input.length);

		Arrays.sort(check);
		QuickSort.sort(input);

		System.out.println(Arrays.equals(check, input));
	}
}