/**
 * Search in a 2D sorted array.
 */
public class P127 {
	public static boolean search(int[][] input, int x) {
		int cols = input[0].length - 1;
		int row = 0;

		while (cols >= 0 && row < input.length) {
			if (input[row][cols] < x) {
				row++;
			} else if (input[row][cols] > x) {
				cols--;
			} else return true;
		}

		return false;
	}

	public static void main(String[] args) {
		int[][] input = new int[][]{
			{-1, 2, 4, 4, 6},
			{1, 5, 5, 9, 21},
			{3, 6, 6, 9, 22},
			{3, 6, 8, 10, 24},
			{6, 8, 9, 12, 25},
			{8, 10, 12, 13, 40},
		};

		System.out.println(search(input, 7));
		System.out.println(search(input, 8));
		System.out.println(search(input, 35));
		System.out.println(search(input, 40));
		System.out.println(search(input, 4));
		System.out.println(search(input, 10));
		System.out.println(search(input, 13));
		System.out.println(search(input, 11));
	}
}