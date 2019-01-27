import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree<T> {
	private static final double MAX_X = 10.0;
	private static final double MAX_Y = 10.0;

	private class Node {
		Point2D point;
		RectHV plane;
		T value;
		Node left, right;
		Node(Point2D point, RectHV plane, T value) {
			this.point = point;
			this.plane = plane;
			this.value = value;
		}
	}

	private Node root;

	public T get(Point2D p) {
		return get(root, p, true);
	}

	private T get(Node n, Point2D p, boolean xCmp) {
		if (n == null) return null;
		else if (p.x() == n.point.x() && p.y() == n.point.y()) return n.value;
		else if ((xCmp && (p.x() < n.point.x())) || (!xCmp && (p.y() < n.point.y()))) return get(n.left, p, !xCmp);
		else return get(n.right, p, !xCmp);
	}

	public void put(Point2D p, T value) {
		root = put(root, p, value, 0.0, 0.0, MAX_X, MAX_Y, true);
	}

	private Node put(Node n, Point2D p, T value, double x0, double y0, double x1, double y1, boolean xCmp) {
		if (n == null) return new Node(p, new RectHV(x0, y0, x1, y1), value);
		else if (p.x() == n.point.x() && p.y() == n.point.y()) n.value = value;
		else if (xCmp) {
			if (p.x() < n.point.x()) {
				n.left = put(n.left, p, value, x0, y0, n.point.x(), y1, !xCmp);
			} else {
				n.right = put(n.right, p, value, n.point.x(), y0, x1, y1, !xCmp);
			}
		} else {
			if (p.y() < n.point.y()) {
				n.left = put(n.left, p, value, x0, y0, x1, n.point.y(), !xCmp);
			} else {
				n.right = put(n.right, p, value, x0, n.point.y(), x1, y1, !xCmp);
			}
		}

		return n;
	}

	public Iterable<Point2D> range(RectHV query) {
		List<Point2D> points = new ArrayList<>();
		range(root, points, query);
		return points;
	}

	private void range(Node n, List<Point2D> points, RectHV query) {
		if (n == null) return;
		if (query.contains(n.point)) points.add(n.point);
		if (query.intersects(n.plane)) {
			range(n.left, points, query);
			range(n.right, points, query);
		}
	}

	public Point2D nearest(Point2D p) {
		return root != null ? nearest(root, p, root.point, true) : null;
	}

	private Point2D nearest(Node n, Point2D p, Point2D closestSoFar, boolean xCmp) {
		if (n == null) return closestSoFar;
		if (p.distanceSquaredTo(n.point) < p.distanceSquaredTo(closestSoFar)) closestSoFar = n.point;
		if (n.plane.distanceSquaredTo(p) < closestSoFar.distanceSquaredTo(p)) {
			Node queriedFirst, queriedSecond;
			
			if ((xCmp && (p.x() < n.point.x())) || (!xCmp && (p.y() < n.point.y()))) {
				queriedFirst = n.left;
				queriedSecond = n.right;
			} else {
				queriedFirst = n.right;
				queriedSecond = n.left;
			}

			closestSoFar = nearest(queriedFirst, p, closestSoFar, !xCmp);
			closestSoFar = nearest(queriedSecond, p, closestSoFar, !xCmp);
		}
		return closestSoFar;
	}

	public static void main(String[] args) {
		double[] xPoints = {1.8, 1.85, 2.34, 2.52, 3.23, 3.49, 4.91, 5.12, 6.00, 6.71};	
		double[] yPoints = {1.6, 4.71, 0.69, 3.20, 2.39, 4.73, 1.18, 3.21, 4.89, 1.75};
		String[] values = {"alpha", "bravo", "charlie", "delta", "echo", "foxtrot", "gamma", "hotel", "india", "juliett"};
		final int count = xPoints.length;

		KdTree<String> kd = new KdTree<>();
		for (int i = 0; i < count; i++) {
			kd.put(new Point2D(xPoints[i], yPoints[i]), values[i]);
		}

		System.out.println("Get [2.52, 3.20]: " + kd.get(new Point2D(2.52, 3.20)));
		System.out.println("Get [9, 9]: " + kd.get(new Point2D(9, 9)));

		RectHV query = new RectHV(2, 2, 4, 5);
		System.out.println("Range search:");
		for (Point2D p : kd.range(query)) {
			System.out.println(p);
		}

		System.out.println("Nearest neighbors:");
		for (int i = 0; i < count; i++) {
			System.out.println("[" + i + "] " + kd.nearest(new Point2D(xPoints[i], yPoints[i])));
		}
	}
}