import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/cracking-the-safe
 */
public class CrackSafe {
	public String crackSafe(int n, int k) {
		if (n == 0 || k == 0) return "";

		Set<String> seen = new HashSet<String>();
		StringBuilder result = new StringBuilder();

		char[] start = new char[n-1];
		Arrays.fill(start, '0');
		String pre = new String(start);

		dfs(pre, k, seen, result);

		result.append(pre);

		return result.toString();
	}

	private void dfs(String pre, int k, Set<String> seen, StringBuilder result) {
		for (int x = 0; x < k; x++) {
			String combination = pre + x;
			if (!seen.contains(combination)) {
				seen.add(combination);
				dfs(combination.substring(1), k, seen, result);
				result.append(x);
			}
		}
	}
}