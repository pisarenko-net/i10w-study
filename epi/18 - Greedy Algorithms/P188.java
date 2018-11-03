/**
 * Compute maximum amount of water trapped between two vertical lines.
 */
public class P188 {
	public static int getMaxTrapped(int[] lines) {
		int i = 0, j = lines.length - 1, max = 0;

		while (i < j) {
			int width = j - i;
			max = Math.max(max, width * Math.min(lines[i], lines[j]));
			if (lines[i] > lines[j]) j--;
			else if (lines[j] > lines[i]) i++;
			else {
				i++;
				j--;
			}
		}

		return max;
	}

	public static void main(String[] args) {
		System.out.println(getMaxTrapped(new int[]{1, 2, 1, 3, 4, 4, 5, 6, 2, 1, 3, 1, 3, 2, 1, 2, 4, 1}));
	}
}