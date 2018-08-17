public class BasicBitwise {
	public static int set(int var, int i) {
		return var | (1 << i);
	}

	public static int clear(int var, int i) {
		return var & ~(1 << i);
	}

	public static int flip(int var, int i) {
		return var ^ (1 << i);
	}

	public static int modify(int var, int i, boolean val) {
		int newVal = val ? 1 : 0;
		return (var & ~(1 << i)) | (newVal << i);
	}

	public static boolean isSet(int var, int i) {
		int bitVal = (var >>> i) & 1;
		return bitVal == 1 ? true : false;
	}

	public static int sign(int var) {
		return (var >> 31) | 1;
	}

	public static int abs(int var) {
		int bit31 = var >> 31;
		return (var ^ bit31) - bit31;
	}

	public static void main(String[] args) {
		int var = Integer.parseInt("11101010", 2);

		System.out.println(Integer.toBinaryString(
			set(var, 2)
		));

		System.out.println(Integer.toBinaryString(
			clear(var, 1)
		));

		System.out.println(Integer.toBinaryString(
			flip(var, 1)
		));
		System.out.println(Integer.toBinaryString(
			flip(var, 0)
		));

		System.out.println(Integer.toBinaryString(
			modify(var, 4, true)
		));
		System.out.println(Integer.toBinaryString(
			modify(var, 5, false)
		));

		System.out.println(isSet(var, 1));
		System.out.println(isSet(var, 0));

		System.out.println(sign(32));
		System.out.println(sign(-32));

		System.out.println(abs(-63));
		System.out.println(abs(63));
	}
}