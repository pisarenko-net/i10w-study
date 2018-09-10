import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Compute intersection of two sorted arrays.
 */
public class P141 {
	public static <T extends Comparable<T>> List<T> intersection(List<T> a, List<T> b) {
		Iterator<T> aIterator = a.iterator();
		Iterator<T> bIterator = b.iterator();
		List<T> result = new ArrayList<>();

		T prev = null;
		T aCurr = aIterator.next();
		T bCurr = bIterator.next();

		while (aIterator.hasNext() && bIterator.hasNext()) {
			if (aCurr.equals(bCurr) && !aCurr.equals(prev)) {
				result.add(aCurr);
				aCurr = aIterator.next();
				bCurr = bIterator.next();
				prev = aCurr;
			} else if (aCurr.compareTo(bCurr) < 0) {
				aCurr = aIterator.next();
			} else {
				bCurr = bIterator.next();
			}
		}

		return result;
	}

	public static void main(String[] args) {
		List<Integer> aList = new ArrayList(Arrays.asList(new Integer[]{2, 2, 3, 5, 8, 10, 12, 15}));
		List<Integer> bList = new ArrayList(Arrays.asList(new Integer[]{1, 3, 3, 3, 6, 7, 9, 12, 190}));

		for (Integer i : intersection(aList, bList)) {
			System.out.println(i);
		}
	}
}