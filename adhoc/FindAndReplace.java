/**
 * https://leetcode.com/problems/find-and-replace-in-string/
 */
public class FindAndReplace {
	public static String findReplaceString(String s, int[] indexes, String[] sources, String[] targets) {
		StringBuilder result = new StringBuilder(s);
		int[] offsets = new int[s.length()];

		for (int i = 0; i < indexes.length; i++) {
			int index = indexes[i];
			String source = sources[i];
			String target = targets[i];

			if (s.substring(index, index + source.length()).equals(source)) {
				int offset = offsets[index];
				int start = index + offset;
				int end = start + source.length();

				result.replace(start, end, target);

				if (target.length() != source.length()) {
					for (int j = index + source.length(); j < offsets.length; j++) {
						offsets[j] += target.length() - source.length();
					}
				}
			}
		}

		return result.toString();
	}

	public static void main(String[] args) {
		System.out.println(findReplaceString("abcd", new int[]{0, 2}, new String[]{"a", "cd"}, new String[]{"eee", "ffff"}));
		System.out.println(findReplaceString("vmokgggqzp", new int[]{3, 5, 1}, new String[]{"kg", "ggq", "mo"}, new String[]{"s", "so", "bfr"}));
		System.out.println(findReplaceString("wreorttvosuidhrxvmvo", new int[]{14,12,10,5,0,18}, new String[]{"rxv","dh","ui","ttv","wreor","vo"}, new String[]{"frs","c","ql","qpir","gwbeve","n"}));
	}
}