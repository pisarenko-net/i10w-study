import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Insert a new interval into a sorted list of intervals, merging any overlapping intervals.
 */
public class P146 {
	private static class Interval implements Comparable<Interval> {
		int start;
		int end;

		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public int compareTo(Interval that) {
			int cmp = Integer.compare(this.start, that.start);
			return cmp != 0 ? cmp : Integer.compare(this.end, that.end);
		}

		public String toString() {
			return String.format("[%s, %s]", start, end);
		}
	}

	public static List<Interval> addInterval(List<Interval> intervals, Interval newInterval) {
		List<Interval> result = new ArrayList<>();
		Interval merged = null;
		for (Interval interval : intervals) {
			if (!intersects(interval, newInterval)) {
				if (merged != null) {
					result.add(merged);
					merged = null;
				}
				result.add(interval);
			} else if (merged == null) {
				merged = new Interval(
					Math.min(interval.start, newInterval.start),
					Math.max(interval.end, newInterval.end)
				);
			} else {
				merged.end = interval.end;
			}
		}
		return result;
	}

	private static boolean intersects(Interval interval, Interval other) {
		return (other.start > interval.start && other.start < interval.end) || (interval.start > other.start && interval.start < other.end);
	}

	public static void main(String[] args) {
		List<Interval> input = Arrays.asList(
			new Interval(-4, -1),
			new Interval(0, 2),
			new Interval(3, 6),
			new Interval(7, 9),
			new Interval(11, 12),
			new Interval(14, 17)
		);
		Collections.sort(input);

		System.out.print("input:");
		for (Interval interval : input) System.out.print(" " + interval);
		System.out.println();

		List<Interval> result = addInterval(input, new Interval(1, 8));

		System.out.print("result:");
		for (Interval interval : result) System.out.print(" " + interval);
		System.out.println();
	}
}