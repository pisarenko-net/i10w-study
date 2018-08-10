import java.lang.Math;
import edu.princeton.cs.algs4.StdRandom;

public class DoublingRatio {
	public static double measureExecution(Runnable function) {
		long startMs = System.currentTimeMillis();
		function.run();
		return (System.currentTimeMillis() - startMs) / 1000;
	}

	public static void cubeAlgo(int[] a) {
		int count = StdRandom.uniform(10);
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				for (int x = 0; x < a.length; x++) {
					StdRandom.uniform(100);
				}
			}
		}
	}

	public static void main(String[] args) {
		double prev = 1;
		for (int N = 100; ; N += N) {
			int[] input = new int[N];
			for (int i = 0; i < N; i++) input[i] = StdRandom.uniform(-1_000_000, 1_000_000);
			double duration = measureExecution(() -> cubeAlgo(input));
			double ratio = duration / prev;
			prev = duration;
			System.out.format("%7d\t\t\t%5.2f\t%5.2f\n", N, duration, ratio);
		}
	}
}