import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Implement a Stack with a "max" operation.
 */
public class P91<T extends Comparable<T>> {
	private Deque<T> stack = new LinkedList<>();
	private Deque<MaxCount> maxStack = new LinkedList<>();

	private class MaxCount {
		T key;
		int count = 1;
	}

	public void push(T key) {
		stack.addFirst(key);
		T currentMax = max();

		if (currentMax == null || key.compareTo(currentMax) > 0) {
			MaxCount newMax = new MaxCount();
			newMax.key = key;
			maxStack.addFirst(newMax);
		} else if (key.compareTo(currentMax) == 0) {
			maxStack.peekFirst().count++;
		}
	}

	public T pop() {
		if (isEmpty()) throw new IndexOutOfBoundsException("stack is empty");
		T key = stack.removeFirst();
		MaxCount max = maxStack.peekFirst();
		if (key.equals(max.key)) {
			if (max.count == 1) {
				maxStack.removeFirst();
			} else {
				max.count--;
			}
		}
		return key;
	}

	public int size() {
		return stack.size();
	}

	public boolean isEmpty() {
		return stack.isEmpty();
	}

	public Iterator<T> iterator() {
		return stack.iterator();
	}

	public T max() {
		if (isEmpty()) throw new IndexOutOfBoundsException("stack is empty");
		return maxStack.isEmpty() ? null : maxStack.peekFirst().key;
	}

	public static void main(String[] args) {
		P91 stack = new P91();
		stack.push(1);
		stack.push(6);
		stack.push(2);
		stack.push(0);
		System.out.println(stack.max());
		stack.push(9);
		System.out.println(stack.max());
		stack.push(9);
		stack.pop();
		System.out.println(stack.max());
		stack.pop();
		System.out.println(stack.max());
	}
}