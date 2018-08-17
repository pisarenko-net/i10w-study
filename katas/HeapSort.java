import java.util.Arrays;
import java.util.Random;

public class HeapSort {
	public static <T extends Comparable<T>> void sort(T[] a) {
		int N = a.length;

		for (int k = N/2; k >= 1; k--) {
			sink(a, k, N);
		}

		while (N > 1) {
			exch(a, 1, N--);
			sink(a, 1, N);
		}
	}

	private static <T extends Comparable<T>> void sink(T[] pq, int k, int N) {
		while (2*k <= N) {
			int j = 2*k;
			if (j < N && less(pq, j, j+1)) j++;
			if (!less(pq, k, j)) break;
			exch(pq, k, j);
			k = j;
		}
	}

	private static <T extends Comparable<T>> void exch(T[] a, int i, int j) {
		T temp = a[i-1];
		a[i-1] = a[j-1];
		a[j-1] = temp;
	}

	private static <T extends Comparable<T>> boolean less(T[] pq, int i, int j) {
		return pq[i-1].compareTo(pq[j-1]) < 0;
	}

	public static void main(String[] args) {
		Integer[] input = new Random().ints(15, 0, 100).boxed().toArray(Integer[]::new);
		Integer[] checked = Arrays.copyOf(input, input.length);

		Arrays.sort(checked);
		HeapSort.sort(input);

		System.out.println(Arrays.equals(checked, input));
	}
}