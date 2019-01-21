import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class EmployeeFreeTime {
	private static class Interval {
	    int start;
	    int end;
	    Interval() { start = 0; end = 0; }
	    Interval(int s, int e) { start = s; end = e; }
	    public String toString() {
	    	return "[" + start + ", " + end + "]";
	    }
	}

	private static class Employee implements Comparable<Employee> {
		List<Interval> schedule;
		Employee(List<Interval> schedule) {
			this.schedule = schedule;
		}
		public int compareTo(Employee that) {
			Interval thisFirst = this.schedule.get(0);
			Interval thatFirst = that.schedule.get(0);
			int startCmp = Integer.compare(thisFirst.start, thatFirst.start);
			return startCmp != 0 ? startCmp : Integer.compare(thisFirst.end, thatFirst.end);
		}
	}

	public static List<Interval> employeeFreeTime(List<List<Interval>> schedules) {
		PriorityQueue<Employee> pq = new PriorityQueue<>();
		for (List<Interval> schedule : schedules) {
			if (!schedule.isEmpty()) pq.add(new Employee(schedule));
		}

		List<Interval> result = new ArrayList<>();
		Interval curr = null;

		while (!pq.isEmpty()) {
			Employee nextEmployee = pq.remove();
			Interval nextInterval = nextEmployee.schedule.get(0);
			if (intersects(curr, nextInterval)) {
				curr = merge(curr, nextInterval);
				nextEmployee.schedule.remove(0);
			} else {
				if (nextInterval.start > curr.end) result.add(new Interval(curr.end, nextInterval.start));
				curr = null;
			}
			if (!nextEmployee.schedule.isEmpty()) pq.add(nextEmployee);
		}

		return result;
	}

	private static boolean intersects(Interval first, Interval second) {
		return (first == null) || (second.start < first.end);
	}

	private static Interval merge(Interval first, Interval second) {
		if (first == null) first = new Interval(second.start, second.end);
		else first.end = Math.max(first.end, second.end);
		return first;
	}

	public static void main(String[] args) {	
		System.out.println(
			employeeFreeTime(
				Arrays.asList(
					new ArrayList<>(Arrays.asList(new Interval(1, 2), new Interval(5, 6))),
					new ArrayList<>(Arrays.asList(new Interval(1, 3), new Interval(4, 10)))
					)
				)
			);

		System.out.println(
			employeeFreeTime(
				Arrays.asList(
					new ArrayList<>(Arrays.asList(new Interval(1, 3), new Interval(6, 7))),
					new ArrayList<>(Arrays.asList(new Interval(2, 4))),
					new ArrayList<>(Arrays.asList(new Interval(2, 5), new Interval(9, 12)))
					)
				)
			);

		System.out.println(employeeFreeTime(new ArrayList<>()));

		System.out.println(employeeFreeTime(new ArrayList<>(new ArrayList<>())));

		// for
		//[[7,24],[29,33],[45,57],[66,69],[94,99]]
		//[[6,24],[43,49],[56,59],[61,75],[80,81]]
		//[[5,16],[18,26],[33,36],[39,57],[65,74]]
		//[[9,16],[27,35],[40,55],[68,71],[78,81]]
		//[[0,25],[29,31],[40,47],[57,87],[91,94]]

		//[[26,27],[36,39],[87,91]] is expected
	}
}