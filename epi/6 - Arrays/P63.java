import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Multiply two arbitrary-precision integers represented as arrays of digits.
  */
public class P63 {
	public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
		int resultSize = num1.size() + num2.size();
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < resultSize; i++) result.add(0);

		boolean isNegative = (num1.get(0) < 0 && num2.get(0) > 0) || (num1.get(0) > 0 && num2.get(0) < 0);
		num1.set(0, Math.abs(num1.get(0)));
		num2.set(0, Math.abs(num2.get(0)));

		Collections.reverse(num1);
		Collections.reverse(num2);

		for (int i = 0; i < num1.size(); i++) {
			for (int j = 0; j < num2.size(); j++) {
				result.set(i + j, result.get(i + j) + num1.get(i) * num2.get(j));
				result.set(i + j + 1, result.get(i + j + 1) + result.get(i + j) / 10);
				result.set(i + j, result.get(i + j) % 10);
			}
		}

		while (result.get(result.size() - 1) == 0 && result.size() != 1) {
			result.remove(result.size() - 1);
		}

		Collections.reverse(result);

		if (isNegative) result.set(0, result.get(0) * -1);

		return result;
	}

	public static void main(String[] args) {
		List<Integer> num1 = Arrays.asList(new Integer[]{4,9,9,1});
		List<Integer> num2 = Arrays.asList(new Integer[]{9,1,3,4,5,5,5,0,0,1,1,9});

		System.out.println(Arrays.toString(multiply(num1, num2).toArray()));
	}
}