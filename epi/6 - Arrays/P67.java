/**
 * Buy and sell stock once.
 */
public class P67 {
	public static int maxProfit(int[] prices) {
		int lowestPrice = Integer.MAX_VALUE;
		int maxProfit = Integer.MIN_VALUE;

		for (int i = 0; i < prices.length; i++) {
			lowestPrice = Math.min(prices[i], lowestPrice);
			maxProfit = Math.max(maxProfit, prices[i] - lowestPrice);
		}

		return maxProfit;
	}

	public static void main(String[] args) {
		int[] prices = new int[]{310, 315, 275, 295, 260, 270, 290, 230, 255, 250};
		System.out.println(maxProfit(prices));
	}
}