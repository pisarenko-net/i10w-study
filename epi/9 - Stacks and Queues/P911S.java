import java.util.Deque;
import java.util.LinkedList;

/**
 * Implement a queue with a stack (two stack solution).
 */
public class P911S<T> {
	private Deque<T> input = new LinkedList<>();
	private Deque<T> output = new LinkedList<>();

	public void push(T value) {
		input.push(value);
	}

	public boolean isEmpty() {
		return input.isEmpty() && output.isEmpty();
	}

	public T pop() {
		if (!output.isEmpty()) {
			return output.pop();
		} else if (!input.isEmpty()) {
			while (!input.isEmpty()) {
				output.push(input.pop());
			}
			return output.pop();
		}

		throw new IllegalStateException("queue is empty");
	}

	public static void main(String[] args) {
		P911S<Integer> queue = new P911S<>();
		queue.push(1);
		queue.push(2);
		queue.push(3);
		queue.push(4);
		queue.push(5);
		while (!queue.isEmpty()) {
			System.out.println(queue.pop());
		}
	}
}