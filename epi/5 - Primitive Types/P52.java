import java.util.Scanner;

public class P52 {
	public static long swapBits(long var, int i, int j) {
		if (((var >>> i) & 1) != ((var >>> j) & 1)) {
			long mask = (1L << i) | (1L << j);
			var ^= mask;
		}
		return var;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		do {
			long input = Long.parseLong(scanner.next(), 2);
			int i = scanner.nextInt();
			int j = scanner.nextInt();
			System.out.println(Long.toBinaryString(swapBits(input, i, j)));
		} while (scanner.hasNextLine());
	}
}
