import java.math.BigInteger;
import java.util.Random;

public class RabinKarp {
	private static final int R = 256;

	private long Q = getRandomPrime();
	private long RM = 1;
	private long patternHash;
	private String pattern;

	public RabinKarp(String pattern) {
		this.pattern = pattern;
		this.patternHash = hash(pattern, pattern.length());
		for (int i = 1; i < pattern.length(); i++) RM = RM * R % Q;
	}

	private long getRandomPrime() {
		return BigInteger.probablePrime(31, new Random()).longValue();
	}

	private long hash(String s, int len) {
		long hash = 0;
		for (int i = 0; i < len; i++) hash = (hash * R + s.charAt(i)) % Q;
		return hash;
	}

	public int search(String input) {
		int inputLength = input.length();
		int patternLength = pattern.length();
		long inputHash = hash(input, patternLength);
		if (inputHash == patternHash) return 0;

		for (int i = patternLength; i < inputLength; i++) {
			inputHash = (inputHash + Q - RM*input.charAt(i - patternLength) % Q) % Q;
			inputHash = (inputHash * R + input.charAt(i)) % Q;
			if (inputHash == patternHash) return i - patternLength + 1;
		}

		return inputLength;
	}

	public static void main(String[] args) {
		String input = args[0];
		String pattern = args[1];

		RabinKarp matcher = new RabinKarp(pattern);
		int offset = matcher.search(input);

		System.out.println("text:    " + input);
		System.out.print("pattern: ");
		for (int i = 0; i < offset; i++) System.out.print(" ");
		System.out.println(pattern);
	}
}