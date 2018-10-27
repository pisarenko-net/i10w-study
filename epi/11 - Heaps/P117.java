import java.util.Comparator;
import java.util.PriorityQueue;

import lombok.AllArgsConstructor;

/**
 * Simplest implementation of a stack using a heap.
 */
public class P117 {
	@AllArgsConstructor
	private class Wrapper implements Comparable<Wrapper> {
		Object value;
		int rank;

		public int compareTo(Wrapper that) {
			return Integer.compare(this.rank, that.rank);
		}
	}

	private PriorityQueue<Wrapper> pq = new PriorityQueue<>(100, Comparator.reverseOrder());
	private int maxRank = 0;

	public void push(Object value) {
		maxRank++;
		pq.add(new Wrapper(value, maxRank));
	}

	public Object pop() {
		Object value = pq.remove().value;
		maxRank--;
		return value;
	}

	public Object peek() {
		return pq.peek().value;
	}

	public static void main(String[] args) {
		P117 stack = new P117();

		stack.push(87);
		stack.push(2);
		stack.push(9);
		stack.push(100);

		System.out.println(stack.peek());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}
}