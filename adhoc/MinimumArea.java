import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/minimum-area-rectangle
 */
public class MinimumArea {
	public static int minAreaRect(int[][] points) {
		Set<Integer> pointSet = new HashSet<>();
		for (int[] point : points) pointSet.add(point[0] * 40000 + point[1]);

		int minArea = Integer.MAX_VALUE;

		for (int i = 0; i < points.length; i++) {
			for (int j = i+1; j < points.length; j++) {
				int[] p1 = points[i], p2 = points[j];

				if (p1[0] == p2[0] || p1[1] == p2[1]) continue;

				if (pointSet.contains(p1[0] * 40000 + p2[1]) && pointSet.contains(p2[0] * 40000 + p1[1])) {
					int area = (p2[1] - p1[1]) * (p2[0] - p1[0]);
					minArea = Math.min(minArea, Math.abs(area));
				}
			}
		}

		return minArea != Integer.MAX_VALUE ? minArea : 0;
	}
}