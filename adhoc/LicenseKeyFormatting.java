public class LicenseKeyFormatting {
	public static String licenseKeyFormatting(String s, int k) {
		StringBuilder sb = new StringBuilder();
		int characters = 0;

		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) == '-') continue;
			if ((characters > 0) && (characters % k == 0)) sb.append('-');
			sb.append(Character.toUpperCase(s.charAt(i)));
			characters++;
		}

		return sb.reverse().toString();
	}

	public static void main(String[] args) {
		System.out.println(licenseKeyFormatting("5F3Z-2e-9-w", 4));
	}
}