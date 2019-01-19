public class MaximumVacationDays {
	public static int maxVacationDays(int[][] flights, int[][] days) {
		int[][] cache = new int[flights.length][days[0].length];
		for (int i = 0; i < cache.length; i++) {
			for (int j = 0; j < cache[0].length; j++) {
				cache[i][j] = -1;
			}
		}

		return maxVacationDays(flights, days, cache, 0, 0);
	}

	private static int maxVacationDays(int[][] flights, int[][] days, int[][] cache, int city, int week) {
		if (week == days[0].length) return 0;

		if (cache[city][week] != -1) return cache[city][week];

		int max = 0;

		for (int nextCity = 0; nextCity < flights.length; nextCity++) {
			if (city == nextCity || flights[city][nextCity] == 1) {
				max = Math.max(max, days[nextCity][week] + maxVacationDays(flights, days, cache, nextCity, week + 1));
			}
		}

		cache[city][week] = max;

		return max;
	}

	public static void main(String[] args) {
		int[][] flights = new int[][]{
			{0, 1, 1},
			{1, 0, 1},
			{1, 1, 0}
		};

		int[][] days = new int[][]{
			{1, 3, 1},
			{6, 0, 3},
			{3, 3, 3}
		};

		System.out.println(maxVacationDays(flights, days));
	}
}