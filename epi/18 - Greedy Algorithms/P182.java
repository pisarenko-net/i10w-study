import java.util.Arrays;

/**
 * Schedule tasks to infinite numbers of workers 2 tasks at a time to minimize total completion time.
 */
public class P182 {
	public static int computeCompletionTime(int[] tasks) {
		Arrays.sort(tasks);
		int max = -1;
		for (int i = 0; i < tasks.length/2; i++) {
			max = Math.max(max, tasks[i] + tasks[tasks.length - i - 1]);
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(computeCompletionTime(new int[]{10, 1, 8, 9}));
	}
}