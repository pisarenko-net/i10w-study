import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Compute a median for online data (streamed).
 */
public class P115 {
	private PriorityQueue<Integer> lowerHalf;
	private PriorityQueue<Integer> higherHalf;

	public P115() {
		lowerHalf = new PriorityQueue<>(100, Collections.reverseOrder());
		higherHalf = new PriorityQueue<>(100);
	}

	public void add(int i) {
		higherHalf.add(i);

		if (higherHalf.size() > (lowerHalf.size() + 1)) {
			lowerHalf.add(higherHalf.remove());
		}
	}

	public double get() {
		if (higherHalf.size() > lowerHalf.size()) {
			return higherHalf.peek();
		} else {
			return (double)(higherHalf.peek() + lowerHalf.peek()) / 2;
		}
	}

	public static void main(String[] args) {
		P115 onlineMedian = new P115();
		int[] input = new int[]{1, 0, 3, 5, 2, 0, 1};

		for (int i : input) {
			onlineMedian.add(i);
			System.out.println(onlineMedian.get());
		}
	}
}