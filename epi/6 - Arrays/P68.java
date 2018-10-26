/**
 * Compute max profit if selling stock at most twice.
 */
public class P68 {
	public static int maxProfit(int[] prices) {
		int minPrice = Integer.MAX_VALUE;
		int maxProfit = Integer.MIN_VALUE;
		int totalMaxProfit = Integer.MIN_VALUE;

		int[] profitFromSellOnDay = new int[prices.length];
		for (int i = 0; i < prices.length; i++) {
			int price = prices[i];
			minPrice = Math.min(minPrice, price);
			maxProfit = Math.max(maxProfit, price - minPrice);
			profitFromSellOnDay[i] = maxProfit;
		}

		int maxPrice = Integer.MIN_VALUE;
		maxProfit = Integer.MIN_VALUE;

		for (int i = prices.length-1; i > 0; i--) {
			int price = prices[i];
			maxPrice = Math.max(maxPrice, price);
			maxProfit = Math.max(maxProfit, maxPrice - price);
			totalMaxProfit = Math.max(totalMaxProfit, maxProfit + profitFromSellOnDay[i-1]);
		}

		return totalMaxProfit;
	}

	public static void main(String[] args) {
		System.out.println(maxProfit(new int[]{12, 11, 13, 9, 12, 8, 14, 13, 15}));
	}
}