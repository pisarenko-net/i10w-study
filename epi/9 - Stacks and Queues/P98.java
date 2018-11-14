import java.util.Random;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Sort a stack using nothing else but push, pop and isEmpty.
 */
public class P98 {
	public static void sort(Deque<Integer> stack) {
		for (int i = 2; i <= stack.size(); i++) find(stack, stack.size() - i, 0);
	}

	private static void find(Deque<Integer> stack, int i, int depth) {
		int top = stack.pop();
		if (depth == i) {
			top = sink(top, stack, depth + 1);
		} else {
			find(stack, i, depth + 1);
		}
		stack.push(top);
	}

	private static int sink(int value, Deque<Integer> stack, int depth) {
		int top = stack.pop();
		if (value < top) {
			int min = stack.isEmpty() ? value : sink(value, stack, depth + 1);
			stack.push(min);
			return top;
		} else {
			stack.push(top);
			return value;
		}
	}

	public static void main(String[] args) {
		Deque<Integer> stack = new LinkedList<>();
		new Random().ints(15, 0, 100).forEach(n -> stack.push(n));
		System.out.println(stack);

		sort(stack);

		System.out.println(stack);
	}
}