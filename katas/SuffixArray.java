import java.util.Arrays;
import java.util.Scanner;

import lombok.AllArgsConstructor;

public class SuffixArray {
	@AllArgsConstructor
	private static class Suffix implements Comparable<Suffix> {
		String text;
		int start;

		int length() {
			return text.length() - start;
		}

		char charAt(int i) {
			return text.charAt(i + start);
		}

		public int compareTo(Suffix that) {
			if (this == that) return 0;
			int count = Math.min(this.length(), that.length());
			for (int i = 0; i < count; i++) {
				if (this.charAt(i) > that.charAt(i)) return 1;
				else if (this.charAt(i) < that.charAt(i)) return -1;
			}
			return Integer.compare(this.length(), that.length());
		}

		public String toString() {
			return text.substring(start);
		}
	}

	private Suffix[] suffixes;

	public SuffixArray(String input) {
		suffixes = new Suffix[input.length()];
		for (int i = 0; i < input.length(); i++) suffixes[i] = new Suffix(input, i);
		Arrays.sort(suffixes);
	}

	public String select(int i) {
		return suffixes[i].toString();
	}

	public int index(int i) {
		return suffixes[i].start;
	}

	public int size() {
		return suffixes.length;
	}

	public int lcp(int i) {
		return lcp(suffixes[i], suffixes[i-1]);
	}

	private int lcp(Suffix a, Suffix b) {
		int length = Math.min(a.length(), b.length());
		for (int i = 0; i < length; i++)
			if (a.charAt(i) != b.charAt(i)) return i;
		return length;
	}

	public int rank(String s) {
		Suffix query = new Suffix(s, 0);
		int lo = 0, hi = size() - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = query.compareTo(suffixes[mid]);
			if (cmp < 0) hi = mid - 1;
			else if (cmp > 0) lo = mid + 1;
			else return mid;
		}
		return lo;
	}

	public static void main(String[] args) {
		String s = new Scanner(System.in).useDelimiter("\\A").next().replaceAll("\\s+", " ").trim();
		SuffixArray suffix = new SuffixArray(s);

		System.out.println("  i ind lcp rnk select");
		System.out.println("---------------------------");

		for (int i = 0; i < s.length(); i++) {
			int index = suffix.index(i);
			String ith = "\"" + s.substring(index, Math.min(index + 50, s.length())) + "\"";
			int rank = suffix.rank(s.substring(index));

			if (i == 0) {
				System.out.printf("%3d %3d %3s %3d %s\n", i, index, "-", rank, ith);
			} else {
				int lcp = suffix.lcp(i);
				System.out.printf("%3d %3d %3d %3d %s\n", i, index, lcp, rank, ith);
			}
		}
	}
}