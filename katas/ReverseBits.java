import java.util.Scanner;

public class ReverseBits {
	private static final int WORD_SIZE = 16;
	private static final int MASK = 0xFFFF;

	private int reverse[];

	public ReverseBits() {
		int tableSize = (int) Math.pow(2, WORD_SIZE);
		reverse = new int[tableSize];
		for (int i = 0; i < tableSize; i++) reverse[i] = reverseDirect(i);
	}

	private int reverseDirect(int x) {
		for (int i = 0; i < WORD_SIZE/2; i++) {
			if (((x >>> i) & 0x1) != ((x >>> (WORD_SIZE - i - 1)) & 0x1)) {
				x ^= (1 << i) | (1 << (WORD_SIZE - i - 1));
			}
		}
		return x & MASK;
	}

	public long reverse(long x) {
		return
			((long)reverse[(int)(x & MASK)] << (3 * WORD_SIZE)) |
			((long)reverse[(int)((x >>> WORD_SIZE) & MASK)] << (2 * WORD_SIZE)) |
			((long)reverse[(int)((x >>> (2*WORD_SIZE)) & MASK)] << WORD_SIZE) |
			((long)reverse[(int)((x >>> (3*WORD_SIZE)) & MASK)]);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ReverseBits calc = new ReverseBits();

		do {
			long input = Long.parseLong(scanner.nextLine(), 2);
			System.out.println(Long.toBinaryString(calc.reverse(input)));
		} while (scanner.hasNextLine());
	}
}