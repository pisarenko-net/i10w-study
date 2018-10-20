/**
 * Compute "edit" distance also known as Levenshtein distance of two strings: minimum number of edits to get from one to another.
 */
public class P172 {
	public static int computeEditDistance(String a, String b) {
		int[][] solutions = new int[a.length()+1][b.length()+1];

		for (int i = 0; i <= a.length(); i++) solutions[i][0] = i;
		for (int j = 0; j <= b.length(); j++) solutions[0][j] = j;

		for (int i = 1; i <= a.length(); i++) {
			for (int j = 1; j <= b.length(); j++) {
				if (a.charAt(i-1) == b.charAt(j-1)) solutions[i][j] = solutions[i-1][j-1];
				else {
					solutions[i][j] = 1 + Math.min(
							solutions[i-1][j],
							Math.min(
								solutions[i-1][j-1],
								solutions[i][j-1]
							)
						);
				}
			}
		}

		return solutions[a.length()][b.length()];
	}

	public static void main(String[] args) {
		System.out.println(computeEditDistance("saturday", "sunday"));
	}
}