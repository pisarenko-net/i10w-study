import java.util.ArrayList;
import java.util.List;

public class LinearProbingHashMap<Key extends Comparable<Key>, Value> {
	private Key[] keys;
	private Value[] values;
	private int count;

	public LinearProbingHashMap(int size) {
		keys = (Key[]) new Comparable[size];
		values = (Value[]) new Object[size];
	}

	private int hash(Object o) {
		return (o.hashCode() & 0x7fffffff) % keys.length;
	}

	public int size() {
		return count;
	}

	public Value get(Key key) {
		for (int i = hash(key); keys[i] != null; i = increment(i)) {
			if (key.equals(keys[i])) return values[i];
		}
		return null;
	}

	private int increment(int i) {
		return (i+1) % keys.length;
	}

	public void put(Key key, Value value) {
		if (count > keys.length/2) resize(keys.length*2);

		int i = hash(key);
		while (keys[i] != null) {
			if (key.equals(keys[i])) {
				values[i] = value;
				return;
			}
			i = increment(i);
		}

		keys[i] = key;
		values[i] = value;
		this.count++;
	}

	private void resize(int newSize) {
		LinearProbingHashMap<Key, Value> map = new LinearProbingHashMap<>(newSize);
		for (int i = 0; i < keys.length; i++) {
			if (keys[i] != null) map.put(keys[i], values[i]);
		}
		this.keys = map.keys;
		this.values = map.values;
	}

	public void delete(Key key) {
		if (get(key) == null) return;

		int i = hash(key);
		while (!key.equals(keys[i])) i = increment(i);
		keys[i] = null;
		values[i] = null;

		i = increment(i);

		while (keys[i] != null) {
			Key keyToRedo = keys[i];
			Value valueToRedo = values[i];
			keys[i] = null;
			values[i] = null;
			this.count--;
			put(keyToRedo, valueToRedo);
			i = increment(i);
		}

		this.count--;
		if (count > 0 && count == keys.length/8) resize(keys.length/2);
	}

	public Iterable<Key> keys() {
		List<Key> result = new ArrayList<>();
		for (int i = 0; i < keys.length; i++) {
			if (keys[i] != null) result.add(keys[i]);
		}
		return result;
	}

	public static void main(String[] args) {
		LinearProbingHashMap<Integer, String> hst = new LinearProbingHashMap<Integer, String>(997);
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