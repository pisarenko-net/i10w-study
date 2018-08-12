import java.util.Arrays;
import java.util.Random;

public class ShellSort {
	public static <T extends Comparable<T>> void sort(T[] a) {
		int N = a.length;
		int h = 1;

		while (h < N/3) h = h*3 + 1;

		while (h >= 1) {
			for (int i = h; i < N; i += h) {
				for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
					exch(a, j, j-h);
				}
			}

			h /= 3;
		}
	}

	public static <T extends Comparable<T>> void exch(T[] a, int i, int j) {
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static <T extends Comparable<T>> boolean less(T a, T b) {
		return a.compareTo(b) < 0;
	}

	public static void main(String[] args) {
		Integer[] input = new Random().ints(15, 0, 100).boxed().toArray(Integer[]::new);
		Integer[] check = Arrays.copyOf(input, input.length);

		Arrays.sort(check);
		ShellSort.sort(input);

		System.out.println(Arrays.equals(check, input));
	}
}