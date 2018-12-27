import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/k-empty-slots/
 */
public class KEmptySlots {
	private Deque<Integer> minDeque;

	public int kEmptySlots(int[] slots, int k) {
		int solution = Integer.MAX_VALUE;

		int[] flowers = new int[slots.length];
		for (int i = 0; i < slots.length; i++) flowers[slots[i]-1] = i+1;

		minDeque = new ArrayDeque<>();
		for (int i = 1; i < k && i < flowers.length; i++) addFlower(flowers[i]);

		for (int i = k + 1; i < flowers.length; i++) {
			int last = flowers[i];
			int first = flowers[i - k - 1];
			int largerExternal = Math.max(first, last);

			removeFlower(first);
			addFlower(flowers[i - 1]);

			if (minDeque.peekLast() > largerExternal || k == 0) solution = Math.min(solution, largerExternal);
		}

		return solution != Integer.MAX_VALUE ? solution : -1;
	}

	private void removeFlower(int flower) {
		if (!minDeque.isEmpty() && minDeque.peekLast() == flower) minDeque.removeLast();
	}

	private void addFlower(int flower) {
		while (!minDeque.isEmpty() && minDeque.peekFirst() > flower) minDeque.removeFirst();
		minDeque.addFirst(flower);
	}

	public static void main(String[] args) {
		KEmptySlots slots = new KEmptySlots();
		System.out.println(slots.kEmptySlots(new int[]{1, 3, 2}, 1));
		System.out.println(slots.kEmptySlots(new int[]{3, 7, 1, 5, 2, 9, 8, 4, 6}, 2));
		System.out.println(slots.kEmptySlots(new int[]{10,2,5,3,9,8,6,1,4,7}, 11));
		System.out.println(slots.kEmptySlots(new int[]{6,5,8,9,7,1,10,2,3,4}, 2));
		System.out.println(slots.kEmptySlots(new int[]{10,1,6,4,2,8,9,7,5,3}, 1));
		System.out.println(slots.kEmptySlots(new int[]{3,9,2,8,1,6,10,5,4,7}, 0));
	}
}