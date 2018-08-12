public class WeightedQuickUnion {
	private int[] sz;
	private int[] id;
	private int count;

	public WeightedQuickUnion(int size) {
		count = size;
		sz = new int[size];
		id = new int[size];
		for (int i = 0; i < size; i++) {
			sz[i] = 1;
			id[i] = i;
		}
	}

	public int find(int p) {
		while (p != id[p]) p = id[p];
		return p;
	}

	public void union(int p, int q) {
		int i = find(p);
		int j = find(q);
		if (i == j) return;

		if (sz[i] > sz[j]) {
			sz[i] += sz[j];
			id[j] = id[i];
		} else {
			sz[j] += sz[i];
			id[i] = id[j];
		}

		count--;
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	public int size() {
		return id.length;
	}

	public int count() {
		return count;
	}

	public static void main(String[] args) {
		WeightedQuickUnion qu = new WeightedQuickUnion(10);
		qu.union(1, 8);
		System.out.println(qu.count() == 9);
		qu.union(2, 7);
		System.out.println(qu.count() == 8);
		qu.union(6, 5);
		System.out.println(qu.count() == 7);
		qu.union(4, 5);
		System.out.println(qu.count() == 6);
		qu.union(0, 4);
		System.out.println(qu.count() == 5);
		qu.union(3, 1);
		System.out.println(qu.count() == 4);
		qu.union(9, 1);
		System.out.println(qu.count() == 3);
		qu.union(1, 2);
		System.out.println(qu.count() == 2);
		qu.union(9, 4);
		System.out.println(qu.count() == 1);
	}
}