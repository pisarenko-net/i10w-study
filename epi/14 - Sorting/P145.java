import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Compute maximum number of concurrent events in a calendar/timeline.
 */
public class P145 {
	private static class Event {
		Endpoint start;
		Endpoint end;

		Event(int start, int end) {
			this.start = new Endpoint(true, start);
			this.end = new Endpoint(false, end);
		}
	}

	private static class Endpoint implements Comparable<Endpoint> {
		boolean isStart;
		int time;

		Endpoint(boolean isStart, int time) {
			this.isStart = isStart;
			this.time = time;
		}

		public int compareTo(Endpoint that) {
			int timeCmp = Integer.compare(time, that.time);
			if (timeCmp != 0) return timeCmp;

			return isStart && !that.isStart ? -1 : !isStart && that.isStart ? 1 : 0;
		}
	}

	public static int getMaxConcurrentEvents(Event[] events) {
		List<Endpoint> endpoints = Arrays
			.stream(events)
			.map(event -> Arrays.asList(event.start, event.end))
			.flatMap(l -> l.stream())
			.collect(Collectors.toList());

		Collections.sort(endpoints);

		int maxConcurrentEvents = 0, concurrentEvents = 0;
		for (Endpoint endpoint : endpoints) {
			if (endpoint.isStart) {
				concurrentEvents++;
				maxConcurrentEvents = Math.max(maxConcurrentEvents, concurrentEvents);
			}
			else concurrentEvents--;
		}

		return maxConcurrentEvents;
	}

	public static void main(String[] args) {
		Event[] events = new Event[]{
			new Event(1, 5),
			new Event(6, 10),
			new Event(11, 13),
			new Event(14, 15),
			new Event(2, 7),
			new Event(8, 9),
			new Event(12, 15),
			new Event(4, 5),
			new Event(9, 17),
		};

		System.out.println(getMaxConcurrentEvents(events));
	}
}