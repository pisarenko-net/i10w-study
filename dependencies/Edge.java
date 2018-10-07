import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Edge implements Comparable<Edge> {
	private int v;
	private int w;
	private double weight;

	public double weight() {
		return weight;
	}

	public int either() {
		return v;
	}

	public int from() {
		return v;
	}

	public int other(int vertex) {
		if (vertex == this.v) return this.w;
		else if (vertex == this.w) return this.v;
		throw new IllegalArgumentException("invalid vertice given");
	}

	public int to() {
		return w;
	}

	public int compareTo(Edge other) {
		return Double.compare(this.weight, other.weight);
	}

	public String toString() {
		return String.format("%d-%d %.5f", v, w, weight);
	}
}