import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/fruit-into-baskets
 */
public class FruitIntoBaskets {
	private static final int MAX_FRUIT_TYPES = 2;

	public static int totalFruit(int[] trees) {
		int maxFruit = 1;
		int fruitCount = 1;

		Set<Integer> pickedFruitTypes = new HashSet<>();
		pickedFruitTypes.add(trees[0]);

		Map<Integer, Integer> typeFirstSeenAt = new HashMap<>();
		typeFirstSeenAt.put(trees[0], 0);

		for (int i = 1; i < trees.length; i++) {
			int fruitType = trees[i];
			int prevFruitType = trees[i-1];

			if (!pickedFruitTypes.contains(fruitType)) {
				typeFirstSeenAt.put(fruitType, i);

				if (pickedFruitTypes.size() < MAX_FRUIT_TYPES) {
					pickedFruitTypes.add(fruitType);
				} else {
					pickedFruitTypes.clear();
					pickedFruitTypes.add(prevFruitType);
					pickedFruitTypes.add(fruitType);
					fruitCount = i - typeFirstSeenAt.get(prevFruitType);
				}
			} else if (fruitType != prevFruitType) {
				typeFirstSeenAt.put(fruitType, i);
			}

			fruitCount++;
			maxFruit = Math.max(maxFruit, fruitCount);
		}

		return maxFruit;
	}

	public static void main(String[] args) {
		System.out.println(totalFruit(new int[]{1, 2, 2}));
		System.out.println(totalFruit(new int[]{0, 1, 2, 2}));
		System.out.println(totalFruit(new int[]{1, 2, 3, 2, 2}));
		System.out.println(totalFruit(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4}));
		System.out.println(totalFruit(new int[]{0, 1, 6, 6, 4, 4, 6}));
		System.out.println(totalFruit(new int[]{3,3,3,1,2,1,1,2,3,3,4}));
		System.out.println(totalFruit(new int[]{0,0,5,0,0,1,0,4,0,4}));
	}
}