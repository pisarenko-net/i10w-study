import java.util.Arrays;
import java.util.Random;

public class RadixSort {
	private final static int R = 34;

	public static void sort(int[] a) {
		int[] aux = new int[a.length];
		int[] count = new int[R+1];

		for (int i = 0; i < a.length; i++) count[a[i]+1]++;

		for (int r = 0; r < R; r++) count[r+1] += count[r];

		for (int i = 0; i < a.length; i++) aux[count[a[i]]++] = a[i];

		for (int i = 0; i < a.length; i++) a[i] = aux[i];
	}

	public static void main(String[] args) {
		int[] input = new Random().ints(100, 0, R).toArray();
		int[] check = Arrays.copyOf(input, input.length);

		RadixSort.sort(input);
		Arrays.sort(check);

		System.out.println(Arrays.equals(input, check));
	}
}