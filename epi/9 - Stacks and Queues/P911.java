import java.util.Deque;
import java.util.LinkedList;

/**
 * Implement a queue with a stack (recursive suboptimal solution).
 */
public class P911<T> {
	private Deque<T> stack = new LinkedList<>();

	public void push(T value) {
		if (stack.isEmpty()) {
			stack.push(value);
		} else {
			T top = stack.pop();
			push(value);
			stack.push(top);
		}
	}

	public boolean isEmpty() {
		return stack.isEmpty();
	}

	public T pop() {
		return stack.pop();
	}

	public static void main(String[] args) {
		P911<Integer> queue = new P911<>();
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