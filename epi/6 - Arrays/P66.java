import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Given a sorted array remove any duplicates in place. The tail of the original array is undefined
 * after execution, e.g. could be zeroes or anything.
 */
public class P66 {
	public static void removeDuplicates(Object[] items) {
		if (items.length == 1) return;

		int writeOffset = 1;

		for (int i = 1; i < items.length; i++) {
			Object currentElement = items[i];
			Object previousElement = items[writeOffset - 1];

			if (!currentElement.equals(previousElement)) {
				items[writeOffset] = currentElement;
				writeOffset++;
			}
		}
	}

	public static void main(String[] args) {
		Integer[] input =  IntStream.of(new int[]{1,1,2})
									.boxed()
									.toArray(Integer[]::new);
		removeDuplicates(input);
		System.out.println(Arrays.toString(input));
	}
}