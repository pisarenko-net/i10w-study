import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRooms2 {
	public static class Interval {
	    int start;
	    int end;
	    Interval() { start = 0; end = 0; }
	    Interval(int s, int e) { start = s; end = e; }
	}

	public static int minMeetingRooms(Interval[] intervals) {
		Arrays.sort(intervals, new Comparator<Interval>(){
			public int compare(Interval one, Interval other) {
				return Integer.compare(one.start, other.start);
			}
		});

		int maxRooms = 0;
		PriorityQueue<Interval> pq = new PriorityQueue<Interval>(10, new Comparator<Interval>() {
			public int compare(Interval one, Interval other) {
				return Integer.compare(one.end, other.end);
			}
		});

		for (Interval interval : intervals) {
			while (!pq.isEmpty() && pq.peek().end <= interval.start) {
				pq.remove();
			}

			pq.add(interval);

			maxRooms = Math.max(maxRooms, pq.size());
		}

		return maxRooms;
	}

	public static void main(String[] args) {
		Interval[] intervals = new Interval[]{
			new Interval(0, 30),
			new Interval(5, 10),
			new Interval(15, 20)
		};

		System.out.println(minMeetingRooms(intervals));

		intervals = new Interval[]{
			new Interval(7, 10),
			new Interval(2, 4)
		};

		System.out.println(minMeetingRooms(intervals));

		intervals = new Interval[]{
			new Interval(13, 15),
			new Interval(1, 13)
		};

		System.out.println(minMeetingRooms(intervals));
	}
}