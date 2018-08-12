import java.util.Scanner;

public class ReverseBits {
	private static final int WORD_SIZE = 16;
	private static final int MASK = 0xFFFF;

	private long[] reverse;

	public ReverseBits() {
		int tableSize = (int) Math.pow(2, WORD_SIZE);
		reverse = new long[tableSize];

		for (int i = 0; i < tableSize; i++) {
			reverse[i] = reverseSlow(i);
		}
	}

	private long reverseSlow(int val) {
		for (int i = 0; i < WORD_SIZE/2; i++) {
			if (((val >>> i) & 1) != ((val >>> (WORD_SIZE - i - 1)) & 1)) {
				val ^= (1 << i) | (1 << (WORD_SIZE - i - 1));
			}
		}
		return val;
	}

	public long reverse(long val) {
		return
			(reverse[(int)(val & MASK)] << (3 * WORD_SIZE)) |
			(reverse[(int)((val >>> WORD_SIZE) & MASK)] << (2 * WORD_SIZE)) |
			(reverse[(int)((val >>> (2 * WORD_SIZE)) & MASK)] << WORD_SIZE) |
			(reverse[(int)((val >>> (3 * WORD_SIZE)) & MASK)]);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ReverseBits calc = new ReverseBits();

		do {
			long val = Long.parseLong(scanner.next(), 2);
			System.out.println(Long.toBinaryString(calc.reverse(val)));
		} while (scanner.hasNextLine());
	}
}