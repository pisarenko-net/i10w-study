import java.util.HashMap;
import java.util.Map;

public class BullsAndCows {
    public static String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;

        Map<Character, Integer> availableDigits = new HashMap<>();
        for (int i = 0; i < secret.length(); i++) {
        	availableDigits.put(secret.charAt(i), availableDigits.getOrDefault(secret.charAt(i), 0) + 1);
        }

        for (int i = 0; i < secret.length(); i++) {
        	if (secret.charAt(i) == guess.charAt(i)) {
        		bulls++;
        		int currentCount = availableDigits.get(secret.charAt(i));
        		if (currentCount == 0) cows--;
        		else availableDigits.put(secret.charAt(i), currentCount - 1);
        	} else if (availableDigits.getOrDefault(guess.charAt(i), 0) > 0) {
        		cows++;
        		availableDigits.put(guess.charAt(i), availableDigits.get(guess.charAt(i)) - 1);
        	}
        }

        return "" + bulls + "A" + cows + "B";
    }

	public static void main(String[] args) {
		System.out.println(getHint("1807", "7810"));
		System.out.println(getHint("1123", "0111"));
		System.out.println(getHint("1122", "2211"));
		System.out.println(getHint("1122", "1222"));
	}
}