/**
 * Compute multiplication of two positive integers using bitwise operators, equality check and
 * shifts.
 */
public class P55 {
	public static long multiply(long x, long y) {
		long sum = 0;
		while (x != 0) {
			if ((x & 1) != 0) {
				sum = add(sum, y);
			}
			x >>>= 1;
			y <<= 1;
		}
		return sum;
	}

	private static long add(long a, long b) {
		long tempA = a, tempB = b, k = 1, carryin = 0, sum = 0;

		while (tempA != 0 || tempB != 0) {
			long ak = a & k, bk = b & k;
			long carryout = (ak & bk) | (ak & carryin) | (bk & carryin);
			sum |= (ak ^ bk ^ carryin);
			carryin = carryout << 1;
			k <<= 1;
			tempA >>>= 1;
			tempB >>>= 1;
		}

		return sum | carryin;
	}

	public static void main(String[] args) {
		System.out.println(multiply(21, 78)); // 1638
	}
}