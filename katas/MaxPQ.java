import java.util.Arrays;
import java.util.Random;

public class MaxPQ<T extends Comparable<T>> {
	private int N;
	private T[] pq;

	public MaxPQ(int size) {
		pq = (T[]) new Comparable[size+1];
	}

	public void add(T key) {
		pq[++N] = key;
		swim(N);
	}

	public T remove() {
		T max = pq[1];
		exch(1, N--);
		pq[N+1] = null;
		sink(1);
		return max;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}

	private void exch(int i, int j) {
		T temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}

	public void sink(int k) {
		while (2*k <= N) {
			int j = 2*k;
			if (j < N && less(j, j+1)) j++;
			if (!less(k, j)) break;
			exch(k, j);
			k = j;
		}
	}

	public void swim(int k) {
		while (k > 1 && less(k/2, k)) {
			exch(k, k/2);
			k /= 2;
		}
	}

	public static void main(String[] args) {
		MaxPQ<Integer> pq = new MaxPQ<>(100);
		new Random().ints(15, 0, 100).boxed().forEach(num -> {
			System.out.print(num + " ");
			pq.add(num);
		});
		System.out.println();

		while (!pq.isEmpty()) {
			System.out.println(pq.remove());
		}
	}
}