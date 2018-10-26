import java.util.ArrayList;
import java.util.List;

/**
 * Compute all primes up to n.
 */
public class P69 {
	public static List<Integer> getPrimes(int n) {
		boolean[] notPrime = new boolean[n];
		List<Integer> primes = new ArrayList<>();
		
		for (int candidate = 2; candidate < n; candidate++) {
			if (notPrime[candidate]) continue;
			primes.add(candidate);
			for (int multiplier = 2; candidate*multiplier < n; multiplier++) notPrime[candidate*multiplier] = true;
		}
		return primes;
	}

	public static void main(String[] args) {
		System.out.println(getPrimes(18));
	}
}