import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;

/**
 * Return a list of intervals, with unions of intersecting intervals.
 */
public class P147 {
	private static class Interval implements Comparable<Interval> {
		Endpoint left;
		Endpoint right;

		public static Interval left(Endpoint left) {
			Interval interval = new Interval();
			interval.left = left;
			return interval;
		}

		public Interval right(Endpoint right) {
			this.right = right;
			return this;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			if (left.isOpen) sb.append('(');
			else sb.append('[');
			sb.append(left.value);
			sb.append(' ');
			sb.append(right.value);
			if (right.isOpen) sb.append(')');
			else sb.append(']');
			return sb.toString();
		}

		public int compareTo(Interval that) {
			int leftCmp = left.compareTo(that.left);
			return leftCmp != 0 ? leftCmp : right.compareTo(that.right);
		}
	}

	@AllArgsConstructor
	private static class Endpoint implements Comparable<Endpoint> {
		boolean isOpen;
		int value;

		public int compareTo(Endpoint other) {
			int cmp = Integer.compare(this.value, other.value);
			return cmp != 0 ? cmp : Boolean.compare(this.isOpen, other.isOpen);
		}
	}

	public static Iterable<Interval> union(List<Interval> intervals) {
		List<Interval> result = new ArrayList<>();

		Collections.sort(intervals);
		Interval curr = intervals.get(0);

		for (int i = 0; i < intervals.size(); i++) {
			Interval interval = intervals.get(i);
			if (interval.left.value < curr.right.value || (interval.left.value == curr.right.value && (!interval.left.isOpen || !curr.right.isOpen))) {
				if (interval.right.value > curr.right.value || (interval.right.value == curr.right.value && !interval.right.isOpen)) {
					curr.right = interval.right;
				}
			} else {
				result.add(curr);
				curr = interval;
			}
		}
		result.add(curr);

		return result;
	}

	public static void main(String[] args) {
		List<Interval> input = Arrays.asList(
			Interval.left(new Endpoint(true, 0)).right(new Endpoint(true, 3)),
			Interval.left(new Endpoint(false, 1)).right(new Endpoint(false, 1)),
			Interval.left(new Endpoint(false, 2)).right(new Endpoint(false, 4)),
			Interval.left(new Endpoint(false, 3)).right(new Endpoint(true, 4)),
			Interval.left(new Endpoint(false, 5)).right(new Endpoint(true, 7)),
			Interval.left(new Endpoint(false, 7)).right(new Endpoint(true, 8)),
			Interval.left(new Endpoint(false, 8)).right(new Endpoint(true, 11)),
			Interval.left(new Endpoint(true, 9)).right(new Endpoint(false, 11)),
			Interval.left(new Endpoint(false, 12)).right(new Endpoint(false, 14)),
			Interval.left(new Endpoint(true, 12)).right(new Endpoint(false, 16)),
			Interval.left(new Endpoint(true, 13)).right(new Endpoint(true, 15)),
			Interval.left(new Endpoint(true, 16)).right(new Endpoint(true, 17))
			);
		Collections.shuffle(input);

		for (Interval interval : input) System.out.print(interval + " ");
		System.out.println();

		for (Interval interval : union(input)) System.out.print(interval + " ");
		System.out.println();
	}
}