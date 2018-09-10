import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Normalize a given Linux path, i.e. remove redundant path parts, such as excessive ".." and ".".
 */
public class P94 {
	public static String normalize(String path) {
		Deque<String> tokens = new LinkedList<>();

		if (path.charAt(0) == '/') {
			tokens.push("/");
		}

		for (String token : path.split("/")) {
			if (token.equals("..")) {
				if (tokens.isEmpty() || tokens.peekFirst().equals("..")) {
					tokens.push("..");
				} else if (tokens.peek().equals("/")) {
					throw new IllegalArgumentException("can't go above root");
				} else {
					tokens.pop();	
				}
			} else if (!token.equals("") && !token.equals(".")) {
				tokens.push(token);
			}
		}

		StringBuilder sb = new StringBuilder();
		if (!tokens.isEmpty()) {
			Iterator<String> it = tokens.descendingIterator();
			String prev = it.next();
			sb.append(prev);

			while (it.hasNext()) {
				if (!prev.equals("/")) {
					sb.append("/");
				}
				prev = it.next();
				sb.append(prev);
			}
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(normalize("/usr/lib/../bin/gcc"));
		System.out.println(normalize("scripts///./../scripts/awkscripts/././"));
		System.out.println(normalize("../../src/./java/../java/././com/microsoft"));
	}
}