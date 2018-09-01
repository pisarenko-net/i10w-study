import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * Increment an arbitrary precision integer represented as an array of digits.
 */
public class P62 {
	public static List<Integer> increment(List<Integer> input) {
		ArrayList<Integer> number = new ArrayList<>(input);
		
		for (int i = number.size() - 1; i >= 0; i--) {
			int digit = number.get(i);
			if (digit == 9) {
				number.set(i, 0);
			} else {
				number.set(i, digit + 1);
				return number;
			}
		}

		number.add(0, 1);

		return number;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(increment(Arrays.asList(9, 9, 9)).toArray()));
	}
}