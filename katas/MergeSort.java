import java.util.Arrays;
import java.util.Random;

public class MergeSort {
	public static <T extends Comparable<T>> void sort(T[] a) {
		T[] aux = (T[]) new Comparable[a.length];
		sort(a, 0, a.length - 1, aux);
	}

	private static <T extends Comparable<T>> void sort(T[] a, int lo, int hi, T[] aux) {
		if (hi <= lo) return;
		int mid = lo + (hi - lo) / 2;
		sort(a, lo, mid, aux);
		sort(a, mid + 1, hi, aux);
		merge(a, lo, mid, hi, aux);
	}
	
	private static <T extends Comparable<T>> void merge(T[] a, int lo, int mid, int hi, T[] aux) {
		int i = lo, j = mid + 1;

		for (int k = lo; k <= hi; k++) aux[k] = a[k];

		for (int k = lo; k <= hi; k++) {
			if (i > mid) a[k] = aux[j++];
			else if (j > hi) a[k] = aux[i++];
			else if (less(aux[i], aux[j])) a[k] = aux[i++];
			else a[k] = aux[j++];
		}
	}

	private static <T extends Comparable<T>> boolean less(T a, T b) {
		return a.compareTo(b) < 0;
	}

	public static void main(String[] args) {
		Integer[] input = new Random().ints(15, 0, 100).boxed().toArray(Integer[]::new);
		Integer[] check = Arrays.copyOf(input, input.length);

		Arrays.sort(check);
		MergeSort.sort(input);

		System.out.println(Arrays.equals(check, input));
	}
}