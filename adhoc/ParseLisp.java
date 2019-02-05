import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/parse-lisp-expression/submissions/
 */
public class ParseLisp {
	public static int evaluate(String expression) {
		return evaluate(new HashMap<>(), expression, 0, expression.length());
	}

	private static int evaluate(Map<String, Integer> variables, String expression, int start, int end) {
		// base case: either literal or variable
		if (expression.charAt(start) != '(') {
			String value = expression.substring(start, end);

			try {
				return Integer.parseInt(value);
			} catch (NumberFormatException e) {
				return variables.get(value);
			}
		}

		int operatorStart = start + 1;
		int operatorEnd = expression.charAt(operatorStart) == 'm' ? operatorStart + 4 : operatorStart + 3;

		String operator = expression.substring(operatorStart, operatorEnd);

		if (operator.equals("let")) {
			int current = operatorEnd + 1;

			variables = new HashMap<>(variables);

			// a variable can't start with "(", so we're definitely handling expression
			while (expression.charAt(current) != '(') {
				String varName = getVarName(expression, current);
				current += varName.length();

				// we detected an expression, retreat
				if (expression.charAt(current) == ')') {
					current -= varName.length();
					break;
				}

				int varValueStart = current + 1;
				int varValueEnd = getExpressionEndIndex(expression, varValueStart);
				int varValue = evaluate(variables, expression, varValueStart, varValueEnd);

				variables.put(varName, varValue);

				current += (varValueEnd - varValueStart) + 2;
			}

			return evaluate(variables, expression, current, end - 1);
		} else {
			int operand1Start = operatorEnd + 1;
			int operand1End = getExpressionEndIndex(expression, operand1Start);

			int operand2Start = operand1End + 1;
			int operand2End = getExpressionEndIndex(expression, operand2Start);

			int operand1Value = evaluate(variables, expression, operand1Start, operand1End);
			int operand2Value = evaluate(variables, expression, operand2Start, operand2End);

			if (operator.equals("add")) return operand1Value + operand2Value;
			else if (operator.equals("mult")) return operand1Value * operand2Value;
		}

		throw new IllegalStateException("unsupported operator");
	}

	private static int getExpressionEndIndex(String expression, int start) {
		int opening = 0, index = start;

		do {
			if (expression.charAt(index) == '(') opening++;
			else if (expression.charAt(index) == ')') opening--;
			index++;
		} while (opening > 0 || ((expression.charAt(index) != ' ') && (expression.charAt(index) != ')')));

		return index;
	}

	private static String getVarName(String expression, int start) {
		int index = start;
		while (expression.charAt(index) != ' ' && expression.charAt(index) != ')') index++;
		return expression.substring(start, index);
	}

	public static void main(String[] args) {
		System.out.println(evaluate("(mult 7 2)"));
		System.out.println(evaluate("(add (mult 7 2) 2)"));
		System.out.println(evaluate("(let x 7 y (add 1 3) (mult x y))"));
		System.out.println(evaluate("(let x 3 x 2 x)"));
		System.out.println(evaluate("(let a1 3 b2 (add a1 1) b2)"));
		System.out.println(evaluate("(let x 2 (add (let x 3 (let x 4 x)) x))"));
	}
}