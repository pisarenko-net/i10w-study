import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;

/**
 * Minimize the number of visits of overlapping intervals.
 */
public class P184 {
	@AllArgsConstructor
	private static class Interval implements Comparable<Interval> {
		int start;
		int end;

		boolean intersects(Interval that) {
			return this.start <= that.end && this.end >= that.start;
		}

		Interval intersect(Interval that) {
			return new Interval(Math.max(this.start, that.start), Math.min(this.end, that.end));
		}

		public int compareTo(Interval that) {
			return Integer.compare(this.start, that.start);
		}
	}

	public static Set<Integer> getFewestVisits(List<Interval> intervals) {
		Collections.sort(intervals);

		Interval intersection = intervals.get(0);
		Set<Integer> result = new HashSet<>();

		for (Interval interval : intervals) {
			if (intersection.intersects(interval)) {
				intersection = intersection.intersect(interval);
			} else {
				result.add(intersection.end);
				intersection = interval;
			}
		}
		result.add(intersection.end);
		return result;
	}

	public static void main(String[] args) {
		System.out.println(getFewestVisits(Arrays.asList(
			new Interval(1, 2),
			new Interval(2, 3),
			new Interval(3, 4),
			new Interval(2, 3),
			new Interval(3, 4),
			new Interval(4, 5)
		)));

		System.out.println(getFewestVisits(Arrays.asList(
			new Interval(0, 3),
			new Interval(2, 6),
			new Interval(3, 4),
			new Interval(6, 9)
		)));
	}
}