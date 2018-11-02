import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import lombok.*;

/**
 * Place objects with the same field value together.
 */
public class P148 {
	@AllArgsConstructor
	@EqualsAndHashCode
	@ToString
	private static class Person {
		int age;
		String name;
	}

	public static void group(List<Person> persons) {
		Map<Integer, Integer> counts = new HashMap<>();
		for (Person person : persons) {
			counts.put(person.age, 1 + counts.getOrDefault(person.age, 0));
		}

		int prevOffset = 0;
		Map<Integer, Integer> offsets = new HashMap<>();
		for (Map.Entry<Integer, Integer> countEntry : counts.entrySet()) {
			int age = countEntry.getKey();
			int count = countEntry.getValue();
			offsets.put(age, prevOffset);
			prevOffset += count;
		}

		while (!offsets.isEmpty()) {
			Map.Entry<Integer, Integer> offsetEntry = offsets.entrySet().iterator().next();
			int offset = offsetEntry.getValue();
			int ageAtOffset = persons.get(offset).age;
			Collections.swap(persons, offset, offsets.get(ageAtOffset));
			int ageAtOffsetCount = counts.get(ageAtOffset);
			if (ageAtOffsetCount == 1) {
				offsets.remove(ageAtOffset);
			} else {
				counts.put(ageAtOffset, counts.get(ageAtOffset) - 1);
				offsets.put(ageAtOffset, offsets.get(ageAtOffset) + 1);
			}
		}
	}

	public static void main(String[] args) {
		List<Person> input = Arrays.asList(
			new Person(21, "Tim"),
			new Person(14, "George"),
			new Person(14, "Jenny"),
			new Person(16, "Sam"),
			new Person(17, "Verena"),
			new Person(21, "Jim"),
			new Person(17, "Nadine"),
			new Person(25, "Victor"),
			new Person(25, "Helen"),
			new Person(21, "Hector"),
			new Person(25, "Janice")
			);

		group(input);

		for (Person person : input) System.out.println(person);
	}
}