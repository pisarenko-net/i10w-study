import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.*;

public class ChainingHashMap<Key extends Comparable<Key>, Value> {
	private static final int M = 997;

	@AllArgsConstructor
	private class Pair {
		Key key;
		Value value;
	}

	private List<Pair>[] chains;
	private int count;

	public ChainingHashMap(int size) {
		chains = (List<Pair>[]) new List[size];
		for (int i = 0; i < size; i++) chains[i] = new ArrayList<>();
	}

	public int size() {
		return count;
	}

	private int hash(Object o) {
		return (o.hashCode() & 0x7fffffff) % chains.size;
	}

	public Value get(Key key) {
		for (Pair p : chains[hash(key)]) {
			if (key.equals(p.key)) return p.value;
		}
		return null;
	}

	public void put(Key key, Value value) {
		if (value == null) throw new IllegalArgumentException("value can't be null");

		List<Pair> chain = chains[hash(key)];
		for (Pair p : chain) {
			if (key.equals(p.key)) {
				p.value = value;
				return;
			}
		}

		chain.add(new Pair(key, value));
		this.count++;
	}

	public void delete(Key key) {
		List<Pair> chain = chains[hash(key)];
		Iterator<Pair> pairIterator = chain.iterator();
		while (pairIterator.hasNext()) {
			Pair p = pairIterator.next();
			if (key.equals(p.key)) {
				pairIterator.remove();
				this.count--;
				return;
			}
		}
	}

	public Iterable<Key> keys() {
		List<Key> keys = new ArrayList<>();
		for (int i = 0; i < chains.length; i++) {
			for (Pair p : chains[i]) keys.add(p.key);
		}
		return keys;
	}

	public static void main(String[] args) {
		ChainingHashMap<Integer, String> hst = new ChainingHashMap<Integer, String>(M);
		System.out.println("size: " + hst.size());
		hst.put(2, "hello");
		hst.put(4, "tater");
		hst.put(0, "dentist");
		hst.put(9, "velocity");
		hst.put(7, "grand");
		hst.put(19, "splendor");
		hst.put(25, "gratification");
		hst.put(31, "museum");
		hst.put(41, "trifecta");
		hst.put(0, "united");
		hst.delete(25);
		System.out.println("size: " + hst.size());

		for (Integer i : hst.keys()) {
			System.out.println(i + " : " + hst.get(i));
		}

		System.out.println("get(5): " + hst.get(5));
		System.out.println("get(4): " + hst.get(4));
	}
}