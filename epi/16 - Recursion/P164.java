import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * Compute a power set of a given set.
 */
public class P164 {
	public static void powerSet(List<Integer> input) {
		int size = (int)Math.pow(2, input.size());
		for (int i = 0; i < size; i++) {
			int bitArray = i;
			
			List<Integer> set = new ArrayList<>();
			// this could be replaced by the more efficient isolation of set bit: bit & ~(bit - 1) with loss of clarity
			for (int b = 0; b < input.size(); b++) {
				if (((bitArray >> b) & 0x1) == 1) set.add(input.get(b));
			}
			System.out.println(set);
		}
	}

	public static void main(String[] args) {
		powerSet(Arrays.asList(0, 1, 2));
	}
}