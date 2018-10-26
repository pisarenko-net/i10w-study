import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import lombok.*;

/**
 * Identify buildings that have a view.
 */
public class P97 {
	@AllArgsConstructor
	@ToString
	private static class Building {
		int id;
		int height;
	}

	public static Deque<Building> getBuildingsWithView(List<Building> buildings) {
		Deque<Building> withView = new LinkedList<>();
		for (int i = buildings.size() - 1; i >= 0; i--) {
			Building building = buildings.get(i);
			while (!withView.isEmpty() && building.height >= withView.peek().height) {
				withView.pop();
			}
			withView.push(building);
		}
		return withView;
	}

	public static void main(String[] args) {
		System.out.println(getBuildingsWithView(Arrays.asList(
			new Building(0, 3),
			new Building(1, 1),
			new Building(2, 4),
			new Building(3, 3),
			new Building(4, 4),
			new Building(5, 5)
		)));
	}
}