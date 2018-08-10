import java.util.Arrays;
import java.util.Random;

public class InsertionSort {
	public static <T extends Comparable<T>> void sort(T[] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
				exch(a, j, j-1);
			}
		}
	}

	public static <T extends Comparable<T>> boolean less(T a, T b) {
		return a.compareTo(b) < 0;
	}

	public static <T extends Comparable<T>> void exch(T[] a, int i, int j) {
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		Integer[] input = new Random().ints(15, 0, 100).boxed().toArray(Integer[]::new);
		Integer[] check = Arrays.copyOf(input, input.length);

		sort(input);
		Arrays.sort(check);

		System.out.println(Arrays.equals(input, check));
	}
}