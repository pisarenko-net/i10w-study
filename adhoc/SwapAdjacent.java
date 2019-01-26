/**
 * https://leetcode.com/problems/swap-adjacent-in-lr-string/
 */
public class SwapAdjacent {
	public static boolean canTransform(String start, String end) {
		if (!start.replace("X", "").equals(end.replace("X", ""))) return false;

		int t = 0;
		for (int i = 0; i < start.length(); i++) {
			if (start.charAt(i) == 'L') {
				while (end.charAt(t) != 'L') t++;
				if (i < t) return false;
				t++;
			}
		}

		t = 0;
		for (int i = 0; i < start.length(); i++) {
			if (start.charAt(i) == 'R') {
				while (end.charAt(t) != 'R') t++;
				if (i > t) return false;
				t++;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		System.out.println(canTransform("X", "X"));
		System.out.println(canTransform("X", "L"));
		System.out.println(canTransform("RXX", "XXR"));
		System.out.println(canTransform("RXXLRXRXL", "XRLXXRRLX"));
		System.out.println(canTransform("XXXXXLXXXX", "LXXXXXXXXX"));
	}
}