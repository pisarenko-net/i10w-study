import java.util.ArrayList;
import java.util.List;

/**
 * Generate all possible IP addresses by inserting dots into the input string.
 */
public class P710 {
	public static Iterable<String> getAddresses(String input) {
		List<String> addresses = new ArrayList<>();
		collect(addresses, input, new StringBuilder(), 0, 3);
		return addresses;
	}

	private static void collect(List<String> addresses, String input, StringBuilder address, int i, int remainingDots) {
		if (remainingDots == 0) {
			String octet = input.substring(i, input.length());
			if (isValid(octet)) {
				addresses.add(address.append(octet).toString());
				address.setLength(address.length() - octet.length());
			}
			return;
		}

		for (int len = 1; len <= 3 && (i + len) < input.length(); len++) {
			String octet = input.substring(i, i + len);
			if (isValid(octet)) {
				collect(addresses, input, address.append(octet + '.'), i+len, remainingDots-1);
				address.setLength(address.length() - len - 1);
			}
		}
	}

	private static boolean isValid(String octet) {
		if (octet.charAt(0) == '0' && octet.length() > 1) return false;
		if (octet.length() > 3) return false;
		int value = Integer.parseInt(octet);
		return value <= 255 && value >= 0;
	}

	public static void main(String[] args) {
		for (String address : getAddresses("19216811"))
			System.out.println(address);
	}
}