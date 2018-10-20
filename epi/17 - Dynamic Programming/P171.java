/**
 * "Coin change" problem: given a set of integers and a total count number of combinations.
 */
public class P171 {
	public static int computeCombinations(int total, int[] scores) {
		int[] solutions = new int[total+1];
		solutions[0] = 1;

		for (int i = 0; i < scores.length; i++) {
			int score = scores[i];
			for (int j = score; j < solutions.length; j++) {
				solutions[j] = solutions[j] + solutions[j - score];
			}
		}

		return solutions[total];
	}

	public static void main(String[] args) {
		System.out.println(computeCombinations(12, new int[]{2, 3, 7}));
	}
}