public class BackspaceCompare {
	public static boolean backspaceCompare(String s, String t) {
		int sOffset = s.length() - 1, tOffset = t.length() - 1;
		int sToErase = 0, tToErase = 0;

		while (sOffset >= 0 || tOffset >= 0) {
			while (sOffset >= 0 && (sToErase > 0 || s.charAt(sOffset) == '#')) {
				if (s.charAt(sOffset) == '#') sToErase++;
				else sToErase--;
				sOffset--;
			}

			while (tOffset >= 0 && (tToErase > 0 || t.charAt(tOffset) == '#')) {
				if (t.charAt(tOffset) == '#') tToErase++;
				else tToErase--;
				tOffset--;
			}

			if (sOffset < 0 && tOffset < 0) return true;
			if ((sOffset >= 0 && tOffset < 0) || (sOffset < 0 && tOffset >= 0)) return false;
			if (s.charAt(sOffset) != t.charAt(tOffset)) return false;
			sOffset--;
			tOffset--;
		}

		return sOffset == tOffset;
	}

	public static void main(String[] args) {
		System.out.println(backspaceCompare("ac", "ac"));
		System.out.println(backspaceCompare("ab#c", "ac"));
		System.out.println(backspaceCompare("####", ""));
		System.out.println(backspaceCompare("abcrrr###de", "abcde"));
		System.out.println(backspaceCompare("ab##", "c#d#"));
		System.out.println(backspaceCompare("a##c", "#a#c"));
		System.out.println(backspaceCompare("bxj##tw", "bxo#j##tw"));
		System.out.println(backspaceCompare("bbbextm", "bbb#extm"));
	}
}