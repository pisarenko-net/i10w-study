import java.util.Scanner;

public class P51 {
	private final static int MASK = 0xFFFF;
	private final static int WORD_SIZE = 16;

	private int[] precomputedParity;

	public P51() {
		int tableSize = (int) Math.pow(2, WORD_SIZE);
		precomputedParity = new int[tableSize];

		for (int i = 0; i < precomputedParity.length; i++) {
			precomputedParity[i] = computeParity(i);
		}
	}

	private int computeParity(int val) {
		val ^= val >>> 16;
		val ^= val >>> 8;
		val ^= val >>> 4;
		val ^= val >>> 2;
		val ^= val >>> 1;
		return val & 0x1;
	}

	public int parity(long val) {
		return (
			precomputedParity[(int)(val >>> (3 * WORD_SIZE) & MASK)] ^
			precomputedParity[(int)(val >>> (2 * WORD_SIZE) & MASK)] ^
			precomputedParity[(int)(val >>> WORD_SIZE & MASK)] ^
			precomputedParity[(int)(val & MASK)]
		);
	}

	public static void main(String[] args) {
		P51 parityComputer = new P51();
		Scanner scanner = new Scanner(System.in);

		do {
			String input = scanner.next();
			System.out.println(parityComputer.parity(Long.parseLong(input, 2)));
		} while (scanner.hasNextLine());
	}
}