import java.util.Random;

/**
 * Generate random uniform integers in the given range [a, b] using one bit random generator.
 */
public class P510 {
	public static int random(int a, int b) {
		int range = b - a + 1, result;

		do {
			result = 0;
			for (int i = 0; (1 << i) < range; i++) {
				result = (result << 1) | randomBit();
			}
		} while (result >= range);

		return result + a;
	}

	private static int randomBit() {
		return new Random().nextInt(2);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.print(random(5, 10) + " ");
		}
		System.out.println();
	}
}