public class NextClosestTime {
	public static String nextClosestTime(String time) {
		int[] availableDigits = getAvailableDigits(time);
		int start = getStartInMinutes(availableDigits);
		int nextTime = Integer.MAX_VALUE;

		for (int firstHourDigit : availableDigits) {
			for (int secondHourDigit : availableDigits) {
				for (int firstMinuteDigit : availableDigits) {
					for (int secondMinuteDigit : availableDigits) {
						int hours = firstHourDigit * 10 + secondHourDigit;
						int minutes = firstMinuteDigit * 10 + secondMinuteDigit;

						if (hours < 24 && minutes < 60) {
							int timeInMinutes = hours * 60 + minutes;
							if (start != timeInMinutes) {
								int delta = getDelta(start, timeInMinutes);
								if (delta < getDelta(start, nextTime)) nextTime = timeInMinutes;
							}
						}
					}
				}
			}
		}

		return nextTime == Integer.MAX_VALUE ? time : convertMinutesToTimeString(nextTime);
	}

	private static int[] getAvailableDigits(String time) {
		return new int[]{
			Character.getNumericValue(time.charAt(0)),
			Character.getNumericValue(time.charAt(1)),
			Character.getNumericValue(time.charAt(3)),
			Character.getNumericValue(time.charAt(4))
		};
	}

	private static int getStartInMinutes(int[] time) {
		int hours = time[0] * 10 + time[1];
		int minutes = time[2] * 10 + time[3];
		return (hours * 60) + minutes;
	}

	private static String convertMinutesToTimeString(int timeInMinutes) {
		int hours = timeInMinutes / 60;
		int minutes = timeInMinutes % 60;
		return String.format("%02d:%02d", hours, minutes);
	}

	private static int getDelta(int start, int now) {
		int delta = start - now;
		return delta < 0 ? -delta : 24*60 - delta;
	}

	public static void main(String[] args) {
		System.out.println(nextClosestTime("01:32"));
	}
}