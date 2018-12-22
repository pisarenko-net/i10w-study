import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A queue with max() operation that returns biggest element still in the queue..
 */
public class P912<T extends Comparable<T>> {
	private Queue<T> queue = new LinkedList<>();
	private Deque<T> max = new LinkedList<>();

	public void push(T value) {
		queue.add(value);
		while (!max.isEmpty()) {
			if (max.getLast().compareTo(value) >= 0) break;
			max.removeLast();
		}
		max.addLast(value);
	}

	public T pop() {
		T value = queue.remove();
		if (value.equals(max.peek())) max.pop();
		return value;
	}

	public T max() {
		return max.peek();
	}

	public static void main(String[] args) {
		P912<Integer> queueWithMax = new P912<>();
		queueWithMax.push(1);
		queueWithMax.push(2);
		queueWithMax.push(6);
		queueWithMax.push(0);
		queueWithMax.push(3);
		queueWithMax.push(1);

		System.out.println(queueWithMax.max());
		System.out.println(queueWithMax.pop());
		System.out.println(queueWithMax.pop());
		System.out.println(queueWithMax.pop());
		System.out.println(queueWithMax.max());
	}
}