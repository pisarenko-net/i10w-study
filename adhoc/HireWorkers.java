import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class HireWorkers {
	private static class Worker implements Comparable<Worker> {
		int quality;
		int wage;

		Worker(int quality, int wage) {
			this.quality = quality;
			this.wage = wage;
		}

		double ratio() {
			return (double) wage / quality;
		}

		public int compareTo(Worker that) {
			return Double.compare(ratio(), that.ratio());
		}
	}

	public static double mincostToHireWorkers(int[] quality, int[] wage, int K) {
		List<Worker> workers = new ArrayList<>();
		for (int i = 0; i < quality.length; i++) workers.add(new Worker(quality[i], wage[i]));
		Collections.sort(workers);

        double solution = Double.POSITIVE_INFINITY;
        int totalQuality = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(100, Comparator.reverseOrder());

        for (Worker worker: workers) {
            pq.add(worker.quality);
            totalQuality += worker.quality;
            if (pq.size() > K) totalQuality -= pq.remove();
            if (pq.size() == K) solution = Math.min(solution, totalQuality * worker.ratio());
        }

		return solution;
	}

	public static void main(String[] args) {
		System.out.println(mincostToHireWorkers(
			new int[]{10, 20, 5},
			new int[]{70, 50, 30},
			2
			));

		System.out.println(mincostToHireWorkers(
			new int[]{3, 1, 10, 10, 1},
			new int[]{4, 8, 2, 2, 7},
			3
			));
	}
}