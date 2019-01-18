import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class OptimalAccountBalancing {
	private static int minTransactions = 0;

	public static int minTransfers(int[][] transactions) {
		Map<Integer, Integer> balances = new HashMap<>();
		for (int[] transaction : transactions) {
			int from = transaction[0];
			int to = transaction[1];
			int amount = transaction[2];

			balances.put(from, balances.getOrDefault(from, 0) - amount);
			balances.put(to, balances.getOrDefault(to, 0) + amount);
		}

		minTransactions = Integer.MAX_VALUE;
		List<Integer> positiveBalances = new ArrayList<>();
		List<Integer> negativeBalances = new ArrayList<>();

		for (int balance : balances.values()) {
			if (balance > 0) positiveBalances.add(balance);
			else if (balance < 0) negativeBalances.add(balance);
		}

		if (positiveBalances.size() > 0 && negativeBalances.size() > 0) countTransactions(positiveBalances, negativeBalances, 0);
		else minTransactions = 0;

		return minTransactions;
	}

	private static void countTransactions(List<Integer> positiveBalances, List<Integer> negativeBalances, int currentTransactions) {
		if (currentTransactions > minTransactions) return;

		if (positiveBalances.isEmpty()) {
			minTransactions = Math.min(minTransactions, currentTransactions);
			return;
		}

		int balance = positiveBalances.get(0);

		for (int i = 0; i < negativeBalances.size(); i++) {
			if (negativeBalances.get(i) == 0) continue;

			int newBalance = balance + negativeBalances.get(i);
			if (newBalance > 0) {
				positiveBalances.set(0, newBalance);
				negativeBalances.set(i, 0);
			} else {
				positiveBalances.remove(0);
				negativeBalances.set(i, newBalance);
			}

			countTransactions(positiveBalances, negativeBalances, currentTransactions + 1);

			if (newBalance > 0) {
				positiveBalances.set(0, balance);
				negativeBalances.set(i, newBalance - balance);
			} else {
				positiveBalances.add(0, balance);
				negativeBalances.set(i, newBalance - balance);
			}
		}
	}

	public static void main(String[] args) {
		int[][] input = new int[][]{
			{0, 1, 10},
			{1, 0, 1},
			{1, 2, 5},
			{2, 0, 5}
		};

		System.out.println(minTransfers(input));

		input = new int[][]{
			{0, 1, 10},
			{2, 0, 5}
		};

		System.out.println(minTransfers(input));
	}
}