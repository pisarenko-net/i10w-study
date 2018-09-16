import java.util.Arrays;

/**
 * Leave one entry per first name.
 */
public class P144 {
	private static class Name implements Comparable<Name> {
		String first;
		String last;

		Name(String first, String last) {
			this.first = first;
			this.last = last;
		}

		public String toString() {
			return first + " " + last;
		}

		public int compareTo(Name that) {
			return this.first.compareTo(that.first);
		}
	}

	public static int removeFirstNameDuplicates(Name[] input) {
		Arrays.sort(input);

		int writeOffset = 1;
		String curr = input[0].first;

		for (int i = 1; i < input.length; i++) {
			if (!curr.equals(input[i].first)) {
				input[writeOffset++] = input[i];
				curr = input[i].first;
			}
		}

		return writeOffset-1;
	}

	public static void main(String[] args) {
		Name[] input = new Name[]{
			new Name("John", "Cam"),
			new Name("Ian", "Mueller"),
			new Name("Casey", "Meier"),
			new Name("John", "Johnson"),
			new Name("Ian", "Doe"),
			new Name("Zag", "Zigler"),
			new Name("Ian", "Noname"),
			new Name("John", "Mulder")
		};

		int newSize = removeFirstNameDuplicates(input);

		System.out.println(Arrays.toString(input));
		System.out.println(newSize);
	}
}