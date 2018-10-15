import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Towers of Hanoi.
 */
public class P161 {
	private static final int NUM_PEGS = 3;

	public static void computeTowerHanoi(int numRings) {
		List<Deque<Integer>> pegs = new ArrayList<>();
		for (int i = 0; i < NUM_PEGS; i++) pegs.add(new LinkedList<>());
		for (int i = numRings; i >= 1; i--) pegs.get(0).addFirst(i);
		computeTowerHanoiSteps(numRings, pegs, 0, 1, 2);
	}

	private static void computeTowerHanoiSteps(int numRingsToMove, List<Deque<Integer>> pegs, int from, int to, int intermediate) {
		if (numRingsToMove > 0) {
			computeTowerHanoiSteps(numRingsToMove - 1, pegs, from, intermediate, to);
			pegs.get(to).addFirst(pegs.get(from).removeFirst());
			System.out.println("Move from " + from + " to " + to);
			computeTowerHanoiSteps(numRingsToMove - 1, pegs, intermediate, to, from);
		}
	}

	public static void main(String[] args) {
		computeTowerHanoi(Integer.parseInt(args[0]));
	}
}