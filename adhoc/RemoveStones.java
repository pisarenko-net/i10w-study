import java.util.HashSet;
import java.util.Set;

public class RemoveStones {
	private static class UF {
		private int[] id;
		private int[] sz;

		public UF(int size) {
			id = new int[size];
			sz = new int[size];
			for (int i = 0; i < size; i++) {
				id[i] = i;
				sz[i] = 1;
			}
		}

		int find(int p) {
			while (id[p] != p) p = id[p];
			return p;
		}

		void union(int p, int q) {
			int i = find(p);
			int j = find(q);
			if (i == j) return;

			if (sz[i] > sz[j]) {
				id[j] = id[i];
				sz[i] += sz[j];
			} else {
				id[i] = id[j];
				sz[j] += sz[i];
			}
		}
	}

	public static int removeStones(int[][] stones) {
		UF uf = new UF(20000);

		for (int[] stone : stones) {
			uf.union(stone[0], stone[1] + 10000);
		}

		Set<Integer> stoneComponents = new HashSet<>();
		for (int[] stone : stones) {
			stoneComponents.add(uf.find(stone[0]));
		}

		return stones.length - stoneComponents.size();
	}

	public static void main(String[] args) {
		System.out.println(removeStones(new int[][]{
			{0, 0},
			{0, 1},
			{1, 0},
			{1, 2},
			{2, 1},
			{2, 2},
		}));

		System.out.println(removeStones(new int[][]{
			{0, 0},
			{0, 2},
			{1, 1},
			{2, 0},
			{2, 2}
		}));
	}
}