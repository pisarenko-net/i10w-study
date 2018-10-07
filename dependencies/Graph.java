public interface Graph {
	int V();

	int E();

	Iterable<Integer> adjacentVertices(int v);
}