/**
 * Checks if a string is palindromic, ignoring any nonalphanumeric characters.
 */
public class P75 {
	public static boolean isPalindromic(String input) {
		int i = 0, j = input.length() - 1;

		while (i < j) {
			while (!Character.isDigit(input.charAt(i)) && !Character.isLetter(input.charAt(i))) i++;
			while (!Character.isDigit(input.charAt(j)) && !Character.isLetter(input.charAt(j))) j--;
			if (Character.toLowerCase(input.charAt(i)) != Character.toLowerCase(input.charAt(j))) return false;
			i++;
			j--;
		}

		return true;
	}

	public static void main(String[] args) {
		System.out.println(isPalindromic("A man, a plan, a canal, Panama"));
		System.out.println(isPalindromic("Ray a ray"));
	}
}