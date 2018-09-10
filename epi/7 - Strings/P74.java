import java.util.Arrays;

/**
 * Remove 'b's from the string and replace 'a's with 'dd' using O(1) space and in O(n) time.
 */
public class P74 {
	public static int encode(char[] input, int size) {
		int writeOffset = 0, aCount = 0;
		for (int i = 0; i < size; i++) {
			if (input[i] == 'a') aCount++;
			if (input[i] != 'b') input[writeOffset++] = input[i];
		}

		int readOffset = writeOffset - 1;
		int finalSize = writeOffset + aCount;
		writeOffset = writeOffset + aCount - 1;
		for (int i = readOffset; i >= 0; i--) {
			if (input[readOffset] == 'a') {
				input[writeOffset--] = 'd';
				input[writeOffset--] = 'd';
			} else {
				input[writeOffset--] = input[readOffset];
			}
			readOffset--;
		}

		return finalSize;
	}

	public static void main(String[] args) {
		char[] input = new char[]{'a', 'c', 'd', 'b', 'b', 'c', 'a'};
		System.out.println(encode(input, 7));
		System.out.println(Arrays.toString(input));
	}
}