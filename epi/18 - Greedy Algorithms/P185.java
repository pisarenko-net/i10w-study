import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Find 3 numbers that sum to a given number.
 */
public class P185 {
	public static List<Integer> threeSum(List<Integer> input, int sum) {
		Collections.sort(input);
		List<Integer> result = new ArrayList<>();

		for (int i = 0; i < input.size(); i++) {
			int n = input.get(i);
			int remaining = sum - n;
			for (int lo = 0, hi = input.size()-1; hi > lo;) {
				int twoSum = input.get(lo) + input.get(hi);
				if (twoSum < remaining) lo++;
				else if (twoSum > remaining) hi--;
				else return Arrays.asList(input.get(lo), input.get(hi), n);
			}
		}

		return Collections.emptyList();
	}

	public static void main(String[] args) {
		System.out.println(threeSum(Arrays.asList(11, 2, 5, 7, 3), 21));
	}
}