import java.util.Scanner;

public class P54 {
	public static final int UNSIGNED_INT_BITS = 31;

	public static int closest(int val) {
		for (int i = 1; i < UNSIGNED_INT_BITS; i++) {
			if ((1 & (val >> i)) != (1 & (val >> (i-1)))) {
				return val ^ ((1 << i) | (1 << (i-1)));
			}
		}
		throw new IllegalArgumentException("all zeroes or all ones detected");
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		do {
			int val = Integer.parseInt(scanner.next(), 2);
			System.out.println(Integer.toBinaryString(closest(val)));
		} while (scanner.hasNextInt());
	}
}