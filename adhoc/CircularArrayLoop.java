public class CircularArrayLoop {
	public static boolean circularArrayLoop(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			if (hasLoop(i, nums)) {
				System.out.println(i);
				return true;
			}
		}

		return false;
	}

	private static boolean hasLoop(int start, int[] nums) {
		int count = 1;

		int currSlow = next(start, nums);
		int currFast = next(currSlow, nums);

		boolean forward = nums[start] > 0;

		while (currFast != currSlow) {
			if (forward && (nums[currFast] < 0 || nums[currSlow] < 0)) return false;
			if (!forward && (nums[currFast] > 0 || nums[currSlow] > 0)) return false;

			count++;
			currSlow = next(currSlow, nums);
			currFast = next(next(currFast, nums), nums);
		}

		System.out.println(start + " " + currSlow + " " + count);

		return (start == currSlow) && count > 1;
	}

	private static int next(int i, int[] nums) {
		return (nums.length + i + nums[i]) % nums.length;
	}

	public static void main(String[] args) {
		System.out.println(circularArrayLoop(new int[]{2, -1, 1, 2, 2}));
		System.out.println(circularArrayLoop(new int[]{-1, 2}));
		System.out.println(circularArrayLoop(new int[]{-2, 1, -1, -2, -2}));
		System.out.println(circularArrayLoop(new int[]{2, -1, 1, -2, -2}));
		System.out.println(circularArrayLoop(new int[]{3, 1, 2}));
	}
}