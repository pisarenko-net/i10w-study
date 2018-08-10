import java.util.Arrays;
import java.util.Random;

public class SelectionSort<T> {
	public static <T extends Comparable<T>> void sort(T[] a) {
		for (int i = 0; i < a.length; i++) {
			int minIndex = i;

			for (int j = i; j < a.length; j++) {
				if (less(a[j], a[minIndex])) {
					minIndex = j;
				}
			}

			exch(a, i, minIndex);
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

		SelectionSort.sort(input);
		Arrays.sort(check);

		System.out.println("Sort seems to work correctly: " + Arrays.equals(input, check));
	}
}