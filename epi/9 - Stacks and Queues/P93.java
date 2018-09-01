import java.util.Deque;
import java.util.LinkedList;

/**
 * Checks if the input string contains a well-balanced bracket expression, e.g. [({})], {}()[][[[]]]. But not )([]), etc.
 */
public class P93 {
	public static boolean isBalanced(String bracketExpression) {
		Deque<Character> brackets = new LinkedList<>();

		for (int i = 0; i < bracketExpression.length(); i++) {
			char curr = bracketExpression.charAt(i);

			if (isOpening(curr)) {
				brackets.push(curr);
			} else {
				if (brackets.isEmpty() || !bracketsMatch(brackets.peek(), curr)) {
					return false;
				}

				brackets.pop();
			}
		}

		return brackets.isEmpty();
	}

	private static boolean isOpening(char character) {
		return character == '{' || character == '(' || character == '[';
	}

	private static boolean bracketsMatch(char opening, char closing) {
		return (opening == '{' && closing == '}') ||
			(opening == '[' && closing == ']') ||
			(opening == '(' && closing == ')');
	}

	public static void main(String[] args) {
		System.out.println(isBalanced("({})[]{}()"));
		System.out.println(isBalanced("({})[(]{}()"));
		System.out.println(isBalanced(")()"));
	}
}